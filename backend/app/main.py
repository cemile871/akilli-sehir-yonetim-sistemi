from fastapi import FastAPI, Depends, HTTPException, status, Query, Form, File, UploadFile
from fastapi.middleware.cors import CORSMiddleware
from sqlalchemy.orm import Session
from datetime import datetime, timedelta
from typing import Optional, List, Dict, Any
import os

from . import models, schemas, crud, auth, ml
from .database import get_db, engine

# Ensure database tables are created (fallback check)
models.Base.metadata.create_all(bind=engine)

app = FastAPI(
    title="Elazığ Akıllı Şehir Yönetim Sistemi API",
    description="Belediye operasyon paneli ve vatandaş mobil uygulaması için merkezi RESTful API servisleri.",
    version="1.0.0"
)

# Configure CORS to allow frontend applications to connect
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # Permits all origins for easy local development and mobile access
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/")
def read_root():
    return {
        "name": "Elazığ Akıllı Şehir Yönetim Sistemi API",
        "version": "1.0.0",
        "docs_url": "/docs",
        "status": "online"
    }

# 1. AUTHENTICATION MODULE
@app.post("/api/v1/auth/login", response_model=schemas.Token, tags=["Kimlik Doğrulama"])
def login(user_credentials: schemas.UserLogin, db: Session = Depends(get_db)):
    db_user = crud.get_user_by_email(db, email=user_credentials.email)
    if not db_user or not auth.verify_password(user_credentials.password, db_user.password_hash):
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Hatalı e-posta veya şifre",
            headers={"WWW-Authenticate": "Bearer"},
        )
    
    access_token = auth.create_access_token(subject=db_user.email, role=db_user.role)
    return {
        "access_token": access_token,
        "token_type": "bearer",
        "role": db_user.role
    }

# 2. SENSOR DATA MODULE
@app.get("/api/v1/sensors/data", response_model=schemas.SensorDataListResponse, tags=["Sensör Verileri"])
def get_sensor_data(
    category: Optional[str] = Query(None, description="Filtrelemek istenen sensör kategorisi (örn: traffic_camera, environment_sensor)"),
    start_date: Optional[datetime] = Query(None, description="Başlangıç tarihi (ISO 8601)"),
    end_date: Optional[datetime] = Query(None, description="Bitiş tarihi (ISO 8601)"),
    page: int = Query(1, ge=1, description="Sayfa numarası"),
    limit: int = Query(100, ge=1, le=100, description="Sayfa başına kayıt limiti"),
    db: Session = Depends(get_db)
):
    offset = (page - 1) * limit
    
    # Retrieve readings
    readings = crud.get_sensor_readings(
        db, category=category, start_date=start_date, end_date=end_date, limit=limit, offset=offset
    )
    # Get total count for pagination
    total_count = crud.get_total_sensor_readings_count(
        db, category=category, start_date=start_date, end_date=end_date
    )
    
    return {
        "status": "success",
        "data": readings,
        "pagination": {
            "total": total_count,
            "page": page,
            "limit": limit
        }
    }

# 3. AI TRAFFIC OPTIMIZATION MODULE
@app.post("/api/v1/ml/optimize-traffic", response_model=schemas.TrafficOptimizeResponse, tags=["Yapay Zeka Analitiği"])
def optimize_traffic(request: schemas.TrafficOptimizeRequest, db: Session = Depends(get_db)):
    # Simulating optimization logic using TensorFlow model in ml.py
    # 1. Check if there are any active incidents in the database
    active_incidents = crud.get_active_incidents_count(db)
    
    # 2. Get current hour to check if it's peak hours (8-9 or 17-19)
    current_hour = datetime.now().hour
    is_peak_hour = current_hour in [8, 9, 17, 18, 19]
    
    # 3. Get recent vehicle count average to feed into the model
    # (Default to 75 if no data is present yet)
    recent_readings = crud.get_sensor_readings(db, category="traffic_camera", limit=5)
    current_vehicle_count = 75
    if recent_readings:
        vehicle_counts = [r.metrics.get("vehicle_count", 75) for r in recent_readings if "vehicle_count" in r.metrics]
        if vehicle_counts:
            current_vehicle_count = int(sum(vehicle_counts) / len(vehicle_counts))
            
    # Run TensorFlow neural network prediction
    prediction, optimization_action = ml.traffic_optimizer.optimize(
        current_vehicle_count=current_vehicle_count,
        is_peak_hour=is_peak_hour,
        active_incidents=active_incidents
    )
    
    return {
        "prediction": prediction,
        "optimization_action": optimization_action
    }

# 4. INCIDENT MANAGEMENT MODULE
@app.get("/api/v1/incidents", response_model=List[schemas.IncidentResponse], tags=["Acil Durum Yönetimi"])
def get_incidents(
    status: Optional[str] = Query(None, description="Olay durumu filtresi ('active' veya 'resolved')"),
    db: Session = Depends(get_db)
):
    return crud.get_incidents(db, status=status)

@app.patch("/api/v1/incidents/{incident_id}", response_model=schemas.IncidentResponse, tags=["Acil Durum Yönetimi"])
def update_incident_status(
    incident_id: str,
    incident_update: schemas.IncidentUpdate,
    current_user: models.User = Depends(auth.verify_admin_role),  # Requires admin privileges
    db: Session = Depends(get_db)
):
    db_incident = crud.update_incident(db, incident_id=incident_id, incident_update=incident_update)
    if not db_incident:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Olay bulunamadı"
        )
    return db_incident

# 5. MOBILE APP COMPATIBILITY ENDPOINTS
@app.post("/api/v1/auth/register", response_model=schemas.AuthResponse, tags=["Mobil Uygulama Uyum"])
def register_user(request: schemas.RegisterRequest, db: Session = Depends(get_db)):
    db_user = crud.get_user_by_email(db, email=request.email)
    if db_user:
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail="Bu e-posta adresi zaten kullanımda."
        )
    
    new_user = crud.create_user_with_name(db, user=request)
    access_token = auth.create_access_token(subject=new_user.email, role=new_user.role)
    
    return {
        "access_token": access_token,
        "refresh_token": "dummy_refresh_token_for_local_dev",
        "user": {
            "id": str(new_user.id),
            "name": new_user.full_name,
            "email": new_user.email,
            "role": new_user.role
        }
    }

@app.post("/api/v1/auth/logout", tags=["Mobil Uygulama Uyum"])
def logout_mobile():
    return {"status": "success", "message": "Oturum kapatıldı."}

@app.get("/api/v1/traffic/current", response_model=List[schemas.TrafficDataMobile], tags=["Mobil Uygulama Uyum"])
def get_current_traffic_mobile(db: Session = Depends(get_db)):
    sensors = db.query(models.Sensor).filter(models.Sensor.category == "traffic_camera").all()
    result = []
    for s in sensors:
        reading = db.query(models.SensorReading).filter(models.SensorReading.sensor_id == s.id).order_by(models.SensorReading.recorded_at.desc()).first()
        
        vehicle_count = 75
        avg_speed = 50.0
        if reading and reading.metrics:
            vehicle_count = int(reading.metrics.get("vehicle_count", 75))
            avg_speed = float(reading.metrics.get("avg_speed", 50.0))
            
        road_status = "normal"
        # Clean junction name to query incidents table
        j_name = s.name.replace(" Trafik Kamerası", "")
        active_inc = db.query(models.Incident).filter(
            models.Incident.title.like(f"%{j_name}%"),
            models.Incident.status == "active"
        ).first()
        if active_inc:
            road_status = "accident" if "Kaza" in active_inc.description else "closed"
            
        density = "FREE"
        if vehicle_count > 140:
            density = "CONGESTED"
        elif vehicle_count > 100:
            density = "HEAVY"
        elif vehicle_count > 50:
            density = "MODERATE"
            
        result.append({
            "sensorId": s.id,
            "vehicleCount": vehicle_count,
            "avgSpeed": avg_speed,
            "roadStatus": road_status,
            "densityLevel": density,
            "timestamp": datetime.now().isoformat()
        })
    return result

@app.get("/api/v1/air-quality/current", response_model=schemas.AirQualityDataMobile, tags=["Mobil Uygulama Uyum"])
def get_current_air_quality_mobile(db: Session = Depends(get_db)):
    reading = db.query(models.SensorReading).join(models.Sensor).filter(models.Sensor.category == "environment_sensor").order_by(models.SensorReading.recorded_at.desc()).first()
    
    aqi = 35
    pm25 = 12.0
    noise = 55.0
    if reading and reading.metrics:
        aqi = int(reading.metrics.get("aqi", 35))
        pm25 = float(reading.metrics.get("pm25", 12.0))
        noise = float(reading.metrics.get("noise_db", 55.0))
        
    advice = "Hava kalitesi iyi, açık hava aktiviteleri için uygun."
    if aqi > 100:
        advice = "Hava kalitesi hassas gruplar için sağlıksız. Maske takılması önerilir."
    elif aqi > 50:
        advice = "Hava kalitesi orta düzeyde."
        
    return {
        "aqiScore": aqi,
        "pm25": pm25,
        "pm10": pm25 * 1.5,
        "co2": 415.0,
        "no2": 18.0,
        "healthAdvice": advice,
        "timestamp": datetime.now().isoformat()
    }

@app.get("/api/v1/incidents/active", response_model=List[schemas.IncidentMobile], tags=["Mobil Uygulama Uyum"])
def get_active_incidents_mobile(db: Session = Depends(get_db)):
    active_incidents = db.query(models.Incident).filter(models.Incident.status.in_(["active", "pending"])).all()
    result = []
    
    cat_map_rev = {
        "traffic": "Trafik Kazası",
        "fire": "Yangın",
        "water": "Su Baskını",
        "infrastructure": "Altyapı Arızası",
        "road_damage": "Yol Hasarı",
        "other": "Diğer"
    }
    
    for idx, inc in enumerate(active_incidents, 1):
        lat, lng = 38.6748, 39.2225
        if inc.latitude is not None and inc.longitude is not None:
            lat, lng = inc.latitude, inc.longitude
        else:
            if "Tofaş" in inc.title:
                lat, lng = 38.6582, 39.2081
            elif "İzzet" in inc.title:
                lat, lng = 38.6792, 39.2264
            elif "Palu" in inc.title:
                lat, lng = 38.6821, 39.2510
            elif "Üniversite" in inc.title:
                lat, lng = 38.6715, 39.1984
            
        result.append({
            "id": idx,
            "kategori": cat_map_rev.get(inc.category, inc.category),
            "aciklama": inc.description,
            "enlem": lat,
            "boylam": lng,
            "status": inc.status,
            "reportedBy": inc.reported_by or "Sistem",
            "photoUrls": [],
            "createdAt": inc.reported_at.isoformat()
        })
    return result

@app.get("/api/v1/announcements", response_model=List[schemas.AnnouncementResponse], tags=["Mobil Uygulama Uyum"])
def get_announcements_mobile(
    category: Optional[str] = Query(None),
    page: int = Query(1, ge=1),
    limit: int = Query(20, ge=1),
    db: Session = Depends(get_db)
):
    offset = (page - 1) * limit
    announcements = crud.get_announcements(db, category=category, limit=limit, offset=offset)
    result = []
    for a in announcements:
        result.append({
            "id": a.id,
            "title": a.title,
            "description": a.description,
            "category": a.category,
            "isImportant": a.is_important,
            "createdAt": a.created_at.isoformat(),
            "photoUrl": a.photo_url
        })
    return result

@app.get("/api/v1/announcements/{id}", response_model=schemas.AnnouncementResponse, tags=["Mobil Uygulama Uyum"])
def get_announcement_detail_mobile(id: int, db: Session = Depends(get_db)):
    a = crud.get_announcement_by_id(db, announcement_id=id)
    if not a:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Duyuru bulunamadı"
        )
    return {
        "id": a.id,
        "title": a.title,
        "description": a.description,
        "category": a.category,
        "isImportant": a.is_important,
        "createdAt": a.created_at.isoformat(),
        "photoUrl": a.photo_url
    }

@app.put("/api/v1/users/fcm-token", tags=["Mobil Uygulama Uyum"])
def update_fcm_token():
    return {"status": "success", "message": "FCM Token güncellendi."}

@app.post("/api/v1/incidents", response_model=schemas.BildirimYaniti, tags=["Mobil Uygulama Uyum"])
def report_incident_mobile(
    kategori: str = Form(...),
    aciklama: str = Form(...),
    enlem: float = Form(...),
    boylam: float = Form(...),
    reported_by: Optional[str] = Form(None),
    fotograflar: List[UploadFile] = File([]),
    db: Session = Depends(get_db)
):
    # Map mobile category string to database incident category key
    cat_map = {
        "Trafik Kazası": "traffic",
        "Yangın": "fire",
        "Su Baskını": "water",
        "Altyapı Arızası": "infrastructure",
        "Yol Hasarı": "road_damage",
        "Diğer": "other"
    }
    db_cat = cat_map.get(kategori, "other")
    
    # Identify closest junction for title
    junctions = [
        ("Çarşı", 38.6748, 39.2225),
        ("Tofaş", 38.6582, 39.2081),
        ("İzzet Paşa", 38.6792, 39.2264),
        ("Palu Yolu", 38.6821, 39.2510),
        ("Üniversite", 38.6715, 39.1984)
    ]
    
    closest_j = "Genel"
    min_dist = float('inf')
    for j_name, lat, lng in junctions:
        dist = (enlem - lat)**2 + (boylam - lng)**2
        if dist < min_dist:
            min_dist = dist
            closest_j = j_name
            
    title = f"{closest_j} Kavşağı - {kategori}"
    
    import random
    tracking_id = random.randint(100000, 999999)
    takip_no = f"VAT-{tracking_id}"
    
    # Create database entry
    db_incident = models.Incident(
        title=title,
        description=aciklama,
        category=db_cat,
        status="pending",
        latitude=enlem,
        longitude=boylam,
        reported_by=reported_by or "Vatandaş"
    )
    db.add(db_incident)
    db.commit()
    db.refresh(db_incident)
    
    return {
        "id": tracking_id,
        "takip_no": takip_no
    }


