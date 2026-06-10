import sys
import os
import random
import time
from datetime import datetime, timedelta
from typing import Dict, Any

# Add parent directory to sys.path
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from app.database import SessionLocal
from app import models, crud, schemas, ml

def clean_and_standardize_traffic(raw_data: Dict[str, Any]) -> Dict[str, float]:
    """
    Traffic sensor data cleaning and standardization.
    Ensures metrics are numeric, non-negative, and imputes defaults for missing values.
    """
    # 1. Clean vehicle count
    vc = raw_data.get("vehicle_count")
    try:
        vc = float(vc) if vc is not None else 50.0
        if vc < 0:
            vc = 0.0  # Correct negative anomaly malfunction
        elif vc > 300:
            vc = 200.0 # Cap impossible readings
    except (ValueError, TypeError):
        vc = 50.0 # Impute default value

    # 2. Clean average speed
    speed = raw_data.get("avg_speed")
    try:
        speed = float(speed) if speed is not None else 50.0
        if speed < 0:
            speed = 0.0
        elif speed > 200:
            speed = 120.0
    except (ValueError, TypeError):
        speed = 50.0

    # 3. Clean waiting time
    wait = raw_data.get("waiting_time")
    try:
        wait = float(wait) if wait is not None else 30.0
        if wait < 0:
            wait = 0.0
        elif wait > 300:
            wait = 150.0
    except (ValueError, TypeError):
        wait = 30.0

    return {
        "vehicle_count": round(vc, 1),
        "avg_speed": round(speed, 1),
        "waiting_time": round(wait, 1)
    }

def clean_and_standardize_env(raw_data: Dict[str, Any]) -> Dict[str, float]:
    """
    Environment sensor data cleaning and standardization.
    Ensures metrics are numeric, non-negative, and bounds them.
    """
    # 1. Clean AQI (Air Quality Index)
    aqi = raw_data.get("aqi")
    try:
        aqi = float(aqi) if aqi is not None else 50.0
        if aqi < 0:
            aqi = 0.0
        elif aqi > 500:
            aqi = 150.0
    except (ValueError, TypeError):
        aqi = 50.0

    # 2. Clean PM2.5
    pm25 = raw_data.get("pm25")
    try:
        pm25 = float(pm25) if pm25 is not None else 15.0
        if pm25 < 0:
            pm25 = 0.0
        elif pm25 > 250:
            pm25 = 50.0
    except (ValueError, TypeError):
        pm25 = 15.0

    # 3. Clean Noise level (decibel)
    noise = raw_data.get("noise_db")
    try:
        noise = float(noise) if noise is not None else 55.0
        if noise < 0:
            noise = 0.0
        elif noise > 150:
            noise = 80.0
    except (ValueError, TypeError):
        noise = 55.0

    return {
        "aqi": round(aqi, 1),
        "pm25": round(pm25, 1),
        "noise_db": round(noise, 1)
    }

def create_incident_for_anomaly(db, junction_name: str, category: str, details: str):
    """
    Creates an incident in the database if an anomaly is detected, 
    but checks first to avoid duplicating active alarms for the same junction.
    """
    # Check for active incidents for this junction and category
    title_pattern = f"%{junction_name}%"
    existing_incident = db.query(models.Incident).filter(
        models.Incident.title.like(title_pattern),
        models.Incident.category == category,
        models.Incident.status == "active"
    ).first()

    if not existing_incident:
        title = f"{junction_name} - Kritik Anomali Tespit Edildi"
        description = f"TensorFlow modeli tarafından yapılan analizde {details} tespit edilmiştir."
        incident_in = schemas.IncidentBase(
            title=title,
            description=description,
            category=category,
            status="active"
        )
        crud.create_incident(db, incident=incident_in)
        print(f"[AI ALARM] Otomatik Olay Açıldı: {title}")

def simulate_random_incident(db):
    categories = [
        ("Trafik Kazası", "trafik kazası nedeniyle şerit daralması ve tıkanıklık oluştu.", "traffic"),
        ("Yangın", "bina yangını ihbarı alındı, itfaiye ekipleri müdahale ediyor.", "fire"),
        ("Su Baskını", "ana su hattı patlaması nedeniyle caddede su birikintisi var.", "water"),
        ("Altyapı Arızası", "yol altında elektrik arızası sebebiyle plan dışı çalışma var.", "infrastructure"),
        ("Yol Hasarı", "yolda derin çökme nedeniyle araç geçişleri yavaşlatıldı.", "road_damage"),
        ("Diğer", "kaldırım hasarı ve genel belediye acil müdahale durumu.", "other")
    ]
    junctions = [
        ("Çarşı", 38.6748, 39.2225),
        ("Tofaş", 38.6582, 39.2081),
        ("İzzet Paşa", 38.6792, 39.2264),
        ("Palu Yolu", 38.6821, 39.2510),
        ("Üniversite", 38.6715, 39.1984)
    ]
    
    cat_name, cat_desc, cat_key = random.choice(categories)
    j_name, lat, lng = random.choice(junctions)
    
    # Check if there is already an active incident for this junction to avoid overcrowding
    title_pattern = f"%{j_name}%"
    existing = db.query(models.Incident).filter(
        models.Incident.title.like(title_pattern),
        models.Incident.status == "active"
    ).first()
    
    if not existing:
        title = f"{j_name} Kavşağı - {cat_name}"
        desc = f"{j_name} kavşağında {cat_desc}"
        
        # Save to db
        db_incident = models.Incident(
            title=title,
            description=desc,
            category=cat_key,
            status="active",
            latitude=lat,
            longitude=lng,
            reported_by="Sistem Simülasyonu"
        )
        db.add(db_incident)
        db.commit()
        print(f"[SİMÜLASYON ACİL DURUM] {title} oluşturuldu!")

def simulate_citizen_incident(db):
    categories = [
        ("Trafik Kazası", "Çarpışma sonucu yol tıkandı, ambulans ve polis ekipleri bekleniyor.", "traffic"),
        ("Yangın", "Çöp konteynerinde başlayan yangın binalara sıçrıyor.", "fire"),
        ("Su Baskını", "Rögar taşkını nedeniyle caddede su birikintisi oluştu, araçlar geçemiyor.", "water"),
        ("Altyapı Arızası", "Sokak lambaları yanmıyor, kablolardan kıvılcım çıkıyor.", "infrastructure"),
        ("Yol Hasarı", "Yolda derin çukur oluşmuş, araç lastikleri zarar görüyor.", "road_damage"),
        ("Diğer", "Kaldırım işgali ve sokak aydınlatması arızası.", "other")
    ]
    junctions = [
        ("Çarşı", 38.6748, 39.2225),
        ("Tofaş", 38.6582, 39.2081),
        ("İzzet Paşa", 38.6792, 39.2264),
        ("Palu Yolu", 38.6821, 39.2510),
        ("Üniversite", 38.6715, 39.1984)
    ]
    citizens = [
        "Mehmet Yılmaz", "Ahmet Kaya", "Ayşe Demir", "Fatma Öztürk", "Mustafa Yıldız", "Emine Çelik", "Anonim Vatandaş"
    ]
    
    cat_name, cat_desc, cat_key = random.choice(categories)
    j_name, lat, lng = random.choice(junctions)
    citizen_name = random.choice(citizens)
    
    # Check if there is already a pending incident of this category at this junction
    title_pattern = f"%{j_name}%"
    existing = db.query(models.Incident).filter(
        models.Incident.title.like(title_pattern),
        models.Incident.category == cat_key,
        models.Incident.status == "pending"
    ).first()
    
    if not existing:
        title = f"{j_name} Kavşağı - {cat_name}"
        offset_lat = random.uniform(-0.003, 0.003)
        offset_lng = random.uniform(-0.003, 0.003)
        
        db_incident = models.Incident(
            title=title,
            description=cat_desc,
            category=cat_key,
            status="pending",
            latitude=lat + offset_lat,
            longitude=lng + offset_lng,
            reported_by=citizen_name
        )
        db.add(db_incident)
        db.commit()
        print(f"[MOBİL SİMÜLASYON İHBARI] {citizen_name} tarafından '{title}' bildirildi! (Pending)")

def resolve_old_incidents(db, max_age_seconds=120):
    active_incidents = db.query(models.Incident).filter(models.Incident.status == "active").all()
    for inc in active_incidents:
        now = datetime.now(inc.reported_at.tzinfo) if inc.reported_at.tzinfo else datetime.now()
        age = (now - inc.reported_at).total_seconds()
        if age > max_age_seconds:
            inc.status = "resolved"
            inc.response_time_seconds = random.randint(150, 320)  # 2.5 to 5.3 minutes
            db.commit()
            print(f"[OTOMATİK ÇÖZÜLDÜ] Olay çözüldü: {inc.title} (Müdahale Süresi: {inc.response_time_seconds} sn)")

def collect_and_process_data():
    db = SessionLocal()
    try:
        sensors = crud.get_sensors(db)
        if not sensors:
            print("[Hata] Veritabanında kayıtlı sensör bulunamadı. Lütfen önce init_db.py çalıştırın.")
            return

        print(f"\n--- SENSÖR VERİ AKIŞI BAŞLADI: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')} ---")

        for sensor in sensors:
            junction_name = sensor.name.split(" Trafik")[0].split(" Çevre")[0]
            
            # Simulated raw data generation (introducing 5% chance of anomalies)
            is_anomaly_outlier = random.random() < 0.05
            
            if sensor.category == "traffic_camera":
                # Normal values
                vc = random.uniform(30, 95)
                speed = random.uniform(40, 75)
                wait = random.uniform(15, 45)
                
                # Introduce anomaly
                if is_anomaly_outlier:
                    # Case 1: High traffic jam (high volume, low speed, high wait time)
                    vc = random.uniform(150, 195)
                    speed = random.uniform(5, 12)
                    wait = random.uniform(90, 140)
                    print(f"[Simülasyon] {junction_name} için Trafik Sıkışıklığı anomalisi üretiliyor.")
                
                raw_metrics = {"vehicle_count": vc, "avg_speed": speed, "waiting_time": wait}
                # Clean
                clean_metrics = clean_and_standardize_traffic(raw_metrics)
                # Analyze using TensorFlow Autoencoder
                is_anomaly, loss = ml.anomaly_detector.detect_traffic_anomaly(
                    clean_metrics["vehicle_count"],
                    clean_metrics["avg_speed"],
                    clean_metrics["waiting_time"]
                )
                
                if is_anomaly:
                    details = f"trafik sıkışıklığı (Araç Sayısı: {clean_metrics['vehicle_count']}, Hız: {clean_metrics['avg_speed']} km/s, Bekleme: {clean_metrics['waiting_time']} sn) (Model Kayıp: {loss:.5f})"
                    create_incident_for_anomaly(db, junction_name, "traffic", details)

            elif sensor.category == "environment_sensor":
                # Normal values
                aqi = random.uniform(15, 48)
                pm25 = random.uniform(5, 18)
                noise = random.uniform(40, 62)
                
                # Introduce anomaly
                if is_anomaly_outlier:
                    if random.choice([True, False]):
                        # Case A: Poor Air Quality (dust/pollution)
                        aqi = random.uniform(120, 180)
                        pm25 = random.uniform(40, 85)
                    else:
                        # Case B: Noise Pollution (construction/heavy honking)
                        noise = random.uniform(78, 92)
                    print(f"[Simülasyon] {junction_name} için Çevresel Kirlilik anomalisi üretiliyor.")

                raw_metrics = {"aqi": aqi, "pm25": pm25, "noise_db": noise}
                # Clean
                clean_metrics = clean_and_standardize_env(raw_metrics)
                # Analyze using TensorFlow Autoencoder
                is_anomaly, loss = ml.anomaly_detector.detect_env_anomaly(
                    clean_metrics["aqi"],
                    clean_metrics["pm25"],
                    clean_metrics["noise_db"]
                )
                
                if is_anomaly:
                    details = f"çevresel kirlilik/gürültü (AQI: {clean_metrics['aqi']}, PM2.5: {clean_metrics['pm25']}, Gürültü: {clean_metrics['noise_db']} dB) (Model Kayıp: {loss:.5f})"
                    create_incident_for_anomaly(db, junction_name, "environment", details)
            
            # Save reading to PostgreSQL
            reading_in = schemas.SensorReadingCreate(
                sensor_id=sensor.id,
                metrics=clean_metrics,
                is_anomaly=is_anomaly
            )
            crud.create_sensor_reading(db, reading=reading_in)
            print(f"  -> Okuma Kaydedildi: {sensor.name} | Metrikler: {clean_metrics} | Anomali: {'EVET' if is_anomaly else 'HAYIR'}")

        # Olay çözümleri ve rastgele olay simülasyonu
        resolve_old_incidents(db, max_age_seconds=120)
        if random.random() < 0.05:
            simulate_random_incident(db)
        if random.random() < 0.07:
            simulate_citizen_incident(db)



    except Exception as e:
        print(f"[Hata] Veri toplama ve işleme sırasında hata: {e}")
    finally:
        db.close()

if __name__ == "__main__":
    # If run directly, run once or loop
    if len(sys.argv) > 1 and sys.argv[1] == "--loop":
        print("Sensör Veri Toplayıcı döngü modunda çalışıyor... (Durdurmak için Ctrl+C)")
        try:
            while True:
                collect_and_process_data()
                time.sleep(3)  # Loop every 3 seconds
        except KeyboardInterrupt:
            print("Veri toplayıcı durduruldu.")
    else:
        # Run once
        collect_and_process_data()
