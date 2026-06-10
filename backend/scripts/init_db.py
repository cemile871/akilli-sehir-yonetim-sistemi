import sys
import os

# Add parent directory to sys.path so we can import from app
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from sqlalchemy import text
from app.database import Base, engine, SessionLocal
from app import models, crud, schemas

def init_database():
    print("[Veritabanı] Tablolar oluşturuluyor...")
    Base.metadata.create_all(bind=engine)
    
    db = SessionLocal()
    try:
        # Alter table if needed to add columns (PostgreSQL)
        try:
            print("[Veritabanı] Tablo sütunları güncelleniyor (varsa atlanır)...")
            db.execute(text("ALTER TABLE incidents ADD COLUMN IF NOT EXISTS latitude FLOAT;"))
            db.execute(text("ALTER TABLE incidents ADD COLUMN IF NOT EXISTS longitude FLOAT;"))
            db.execute(text("ALTER TABLE incidents ADD COLUMN IF NOT EXISTS reported_by VARCHAR(100);"))
            db.commit()
        except Exception as err:
            db.rollback()
            print(f"[Veritabanı] Sütun ekleme sırasında hata (normal olabilir): {err}")
        # 1. Seed Admin User
        admin_email = "admin@belediye.gov"
        print(f"[Veritabanı] Yönetici hesabı kontrol ediliyor: {admin_email}")
        db_admin = crud.get_user_by_email(db, email=admin_email)
        if not db_admin:
            user_in = schemas.UserLogin(email=admin_email, password="securepass")
            crud.create_user(
                db, 
                user=user_in, 
                full_name="Abdullah Gümüş (Yönetici)", 
                role="municipality_admin"
            )
            print("[Veritabanı] Yönetici hesabı başarıyla oluşturuldu. (Şifre: securepass)")
        else:
            print("[Veritabanı] Yönetici hesabı zaten mevcut.")

        # 2. Seed Sensors
        # We define Elazığ's 5 major junctions and place traffic camera and environment sensors on them
        junctions = [
            {"name": "Çarşı Kavşağı", "lat": 38.6748, "lng": 39.2225},
            {"name": "Tofaş Kavşağı", "lat": 38.6582, "lng": 39.2081},
            {"name": "İzzet Paşa Kavşağı", "lat": 38.6792, "lng": 39.2264},
            {"name": "Palu Yolu Kavşağı", "lat": 38.6821, "lng": 39.2510},
            {"name": "Üniversite Kavşağı", "lat": 38.6715, "lng": 39.1984}
        ]

        print("[Veritabanı] Kavşaklar ve sensör tohumlama işlemi başlıyor...")
        existing_sensors = db.query(models.Sensor).all()
        existing_names = {s.name for s in existing_sensors}

        for j in junctions:
            # Seed Traffic Camera Sensor
            traffic_sensor_name = f"{j['name']} Trafik Kamerası"
            if traffic_sensor_name not in existing_names:
                sensor_in = schemas.SensorCreate(
                    category="traffic_camera",
                    name=traffic_sensor_name,
                    latitude=j["lat"],
                    longitude=j["lng"]
                )
                crud.create_sensor(db, sensor=sensor_in)
                print(f"  + Sensör Eklendi: {traffic_sensor_name}")

            # Seed Environment Air/Noise Sensor
            env_sensor_name = f"{j['name']} Çevre Sensörü"
            if env_sensor_name not in existing_names:
                sensor_in = schemas.SensorCreate(
                    category="environment_sensor",
                    name=env_sensor_name,
                    latitude=j["lat"],
                    longitude=j["lng"]
                )
                crud.create_sensor(db, sensor=sensor_in)
                print(f"  + Sensör Eklendi: {env_sensor_name}")
                
        # 3. Seed Announcements
        print("[Veritabanı] Başlangıç duyuruları tohumlama işlemi başlıyor...")
        existing_announcements = db.query(models.Announcement).all()
        if not existing_announcements:
            announcements_to_seed = [
                {
                    "title": "Çarşı Kavşağında Planlı Yol Bakım Çalışması",
                    "description": "Belediyemiz tarafından Çarşı Kavşağı üzerinde 05.06.2026 tarihinde 02:00 - 05:00 saatleri arasında yol bakım çalışması gerçekleştirilecektir.",
                    "category": "TRANSPORT",
                    "is_important": True
                },
                {
                    "title": "Yeşil Enerji Modernizasyonu",
                    "description": "Elazığ genelinde akıllı aydınlatma sistemlerine geçiş çalışmaları kapsamında bu hafta Üniversite Kavşağı çevresindeki armatürler LED'lerle yenileniyor.",
                    "category": "ENERGY",
                    "is_important": False
                },
                {
                    "title": "Akıllı Şehir Mobil Uygulamamız Yayında!",
                    "description": "Vatandaşlarımızın belediye hizmetlerine, yol durumuna ve çevresel analiz raporlarına anlık olarak ulaşabileceği yeni mobil uygulamamız yayınlandı.",
                    "category": "GENERAL",
                    "is_important": True
                }
            ]
            for a in announcements_to_seed:
                crud.create_announcement(
                    db,
                    title=a["title"],
                    description=a["description"],
                    category=a["category"],
                    is_important=a["is_important"]
                )
                print(f"  + Duyuru Eklendi: {a['title']}")
        else:
            print("[Veritabanı] Duyurular zaten mevcut.")
            
        # 4. Seed Incidents
        print("[Veritabanı] Başlangıç acil durum olayları tohumlama işlemi başlıyor...")
        existing_incidents = db.query(models.Incident).all()
        if not existing_incidents:
            incidents_to_seed = [
                {
                    "title": "İzzet Paşa Kavşağı - Ambulans Sevkıyatı",
                    "description": "İzzet Paşa → Üniversite güzergahında acil hasta nakli için ambulans geçişi.",
                    "category": "traffic",
                    "status": "active",
                    "latitude": 38.6792,
                    "longitude": 39.2264,
                    "reported_by": "112 Acil Kontrol"
                },
                {
                    "title": "Tofaş Kavşağı - İtfaiye Müdahalesi",
                    "description": "Tofaş → Çarşı güzergahında yangın ihbarı sebebiyle itfaiye ekipleri yola çıktı.",
                    "category": "fire",
                    "status": "resolved",
                    "response_time_seconds": 252, # 4.2 minutes
                    "latitude": 38.6582,
                    "longitude": 39.2081,
                    "reported_by": "110 İtfaiye"
                },
                {
                    "title": "Palu Yolu Kavşağı - Kaza Müdahalesi",
                    "description": "Palu Yolu → İzzet Paşa kavşağında zincirleme kaza için ambulans yönlendirildi.",
                    "category": "traffic",
                    "status": "resolved",
                    "response_time_seconds": 306, # 5.1 minutes
                    "latitude": 38.6821,
                    "longitude": 39.2510,
                    "reported_by": "Sistem"
                },
                {
                    "title": "Çarşı Kavşağı - Su Borusu Patlaması Müdahalesi",
                    "description": "Çarşı → Üniversite kavşağında ana su hattı arızası için ASKİ ekipleri yönlendirildi.",
                    "category": "water",
                    "status": "resolved",
                    "response_time_seconds": 348, # 5.8 minutes
                    "latitude": 38.6748,
                    "longitude": 39.2225,
                    "reported_by": "Vatandaş"
                }
            ]
            for inc in incidents_to_seed:
                db.add(models.Incident(
                    title=inc["title"],
                    description=inc["description"],
                    category=inc["category"],
                    status=inc["status"],
                    response_time_seconds=inc.get("response_time_seconds"),
                    latitude=inc["latitude"],
                    longitude=inc["longitude"],
                    reported_by=inc["reported_by"]
                ))
            db.commit()
            print("  + Başlangıç acil durum olayları başarıyla tohumlandı.")
        else:
            print("[Veritabanı] Acil durum olayları zaten mevcut.")
            
        print("[Veritabanı] Tohumlama işlemi tamamlandı.")
    except Exception as e:
        print(f"[Veritabanı] Hata oluştu: {e}")
    finally:
        db.close()

if __name__ == "__main__":
    init_database()
