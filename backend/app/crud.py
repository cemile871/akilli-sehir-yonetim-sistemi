from sqlalchemy.orm import Session, joinedload
from sqlalchemy import and_
from datetime import datetime
from typing import List, Optional
from . import models, schemas, auth

# User CRUD
def get_user_by_email(db: Session, email: str) -> Optional[models.User]:
    return db.query(models.User).filter(models.User.email == email).first()

def create_user(db: Session, user: schemas.UserLogin, full_name: str, role: str = "citizen") -> models.User:
    hashed_password = auth.get_password_hash(user.password)
    db_user = models.User(
        email=user.email,
        password_hash=hashed_password,
        full_name=full_name,
        role=role
    )
    db.add(db_user)
    db.commit()
    db.refresh(db_user)
    return db_user

# Sensor CRUD
def get_sensors(db: Session) -> List[models.Sensor]:
    return db.query(models.Sensor).all()

def get_sensor_by_id(db: Session, sensor_id: str) -> Optional[models.Sensor]:
    return db.query(models.Sensor).filter(models.Sensor.id == sensor_id).first()

def create_sensor(db: Session, sensor: schemas.SensorCreate) -> models.Sensor:
    db_sensor = models.Sensor(
        category=sensor.category,
        name=sensor.name,
        latitude=sensor.latitude,
        longitude=sensor.longitude
    )
    db.add(db_sensor)
    db.commit()
    db.refresh(db_sensor)
    return db_sensor

# Sensor Reading CRUD
def create_sensor_reading(db: Session, reading: schemas.SensorReadingCreate) -> models.SensorReading:
    db_reading = models.SensorReading(
        sensor_id=reading.sensor_id,
        metrics=reading.metrics,
        is_anomaly=reading.is_anomaly
    )
    db.add(db_reading)
    db.commit()
    db.refresh(db_reading)
    return db_reading

def get_sensor_readings(
    db: Session, 
    category: Optional[str] = None, 
    start_date: Optional[datetime] = None, 
    end_date: Optional[datetime] = None, 
    limit: int = 100, 
    offset: int = 0
) -> List[models.SensorReading]:
    query = db.query(models.SensorReading).options(joinedload(models.SensorReading.sensor))
    
    # Filter by category if specified (requires join with Sensor)
    if category:
        query = query.join(models.Sensor).filter(models.Sensor.category == category)
        
    # Filter by date range
    if start_date:
        query = query.filter(models.SensorReading.recorded_at >= start_date)
    if end_date:
        query = query.filter(models.SensorReading.recorded_at <= end_date)
        
    # Order by newest first
    query = query.order_by(models.SensorReading.recorded_at.desc())
    
    return query.offset(offset).limit(limit).all()

def get_total_sensor_readings_count(
    db: Session,
    category: Optional[str] = None,
    start_date: Optional[datetime] = None,
    end_date: Optional[datetime] = None
) -> int:
    query = db.query(models.SensorReading)
    if category:
        query = query.join(models.Sensor).filter(models.Sensor.category == category)
    if start_date:
        query = query.filter(models.SensorReading.recorded_at >= start_date)
    if end_date:
        query = query.filter(models.SensorReading.recorded_at <= end_date)
    return query.count()

# Incident CRUD
def get_incidents(db: Session, status: Optional[str] = None) -> List[models.Incident]:
    query = db.query(models.Incident)
    if status:
        query = query.filter(models.Incident.status == status)
    return query.order_by(models.Incident.reported_at.desc()).all()

def create_incident(db: Session, incident: schemas.IncidentBase) -> models.Incident:
    db_incident = models.Incident(
        title=incident.title,
        description=incident.description,
        category=incident.category,
        status=incident.status
    )
    db.add(db_incident)
    db.commit()
    db.refresh(db_incident)
    return db_incident

def update_incident(db: Session, incident_id: str, incident_update: schemas.IncidentUpdate) -> Optional[models.Incident]:
    db_incident = db.query(models.Incident).filter(models.Incident.id == incident_id).first()
    if not db_incident:
        return None
    
    db_incident.status = incident_update.status
    if incident_update.response_time_seconds is not None:
        db_incident.response_time_seconds = incident_update.response_time_seconds
        
    db.commit()
    db.refresh(db_incident)
    return db_incident

def get_active_incidents_count(db: Session) -> int:
    return db.query(models.Incident).filter(models.Incident.status == "active").count()

# Register User with Name
def create_user_with_name(db: Session, user: schemas.RegisterRequest) -> models.User:
    hashed_password = auth.get_password_hash(user.password)
    db_user = models.User(
        email=user.email,
        password_hash=hashed_password,
        full_name=user.name,
        role="citizen"  # Registered via app defaults to citizen
    )
    db.add(db_user)
    db.commit()
    db.refresh(db_user)
    return db_user

# Announcement CRUD
def get_announcements(db: Session, category: Optional[str] = None, limit: int = 20, offset: int = 0) -> List[models.Announcement]:
    query = db.query(models.Announcement)
    if category:
        query = query.filter(models.Announcement.category == category.upper())
    return query.order_by(models.Announcement.created_at.desc()).offset(offset).limit(limit).all()

def get_announcement_by_id(db: Session, announcement_id: int) -> Optional[models.Announcement]:
    return db.query(models.Announcement).filter(models.Announcement.id == announcement_id).first()

def create_announcement(
    db: Session, 
    title: str, 
    description: str, 
    category: str, 
    is_important: bool = False, 
    photo_url: Optional[str] = None
) -> models.Announcement:
    db_announcement = models.Announcement(
        title=title,
        description=description,
        category=category.upper(),
        is_important=is_important,
        photo_url=photo_url
    )
    db.add(db_announcement)
    db.commit()
    db.refresh(db_announcement)
    return db_announcement




