from sqlalchemy import Column, String, Integer, Float, DateTime, Boolean, ForeignKey
from sqlalchemy.dialects.postgresql import JSONB
from sqlalchemy.orm import relationship
from sqlalchemy.sql import func
import uuid
from .database import Base

class User(Base):
    __tablename__ = "users"

    id = Column(Integer, primary_key=True, index=True)
    email = Column(String(255), unique=True, index=True, nullable=False)
    password_hash = Column(String(255), nullable=False)
    full_name = Column(String(255), nullable=False)
    role = Column(String(50), default="citizen", nullable=False)  # 'municipality_admin', 'citizen'
    created_at = Column(DateTime(timezone=True), server_default=func.now())

class Sensor(Base):
    __tablename__ = "sensors"

    id = Column(String(36), primary_key=True, default=lambda: str(uuid.uuid4()), index=True)
    category = Column(String(50), nullable=False)  # 'traffic_camera', 'environment_sensor', etc.
    name = Column(String(100), nullable=False)  # e.g., 'Çarşı Kavşağı'
    latitude = Column(Float, nullable=False)
    longitude = Column(Float, nullable=False)
    created_at = Column(DateTime(timezone=True), server_default=func.now())

    readings = relationship("SensorReading", back_populates="sensor", cascade="all, delete-orphan")

class SensorReading(Base):
    __tablename__ = "sensor_readings"

    id = Column(Integer, primary_key=True, index=True)
    sensor_id = Column(String(36), ForeignKey("sensors.id"), nullable=False)
    recorded_at = Column(DateTime(timezone=True), default=func.now(), nullable=False)
    metrics = Column(JSONB, nullable=False)  # e.g. {"vehicle_count": 85, "avg_speed": 42}
    is_anomaly = Column(Boolean, default=False, nullable=False)
    created_at = Column(DateTime(timezone=True), server_default=func.now())

    sensor = relationship("Sensor", back_populates="readings")

class Incident(Base):
    __tablename__ = "incidents"

    id = Column(String(36), primary_key=True, default=lambda: str(uuid.uuid4()), index=True)
    title = Column(String(100), nullable=False)  # e.g., 'Yüksek Trafik Sıkışıklığı'
    description = Column(String(255), nullable=False)
    category = Column(String(50), nullable=False)  # 'traffic', 'air_quality', 'noise', etc.
    status = Column(String(20), default="active", nullable=False)  # 'active', 'resolved'
    reported_at = Column(DateTime(timezone=True), default=func.now(), nullable=False)
    response_time_seconds = Column(Integer, nullable=True)  # Response time when resolved
    created_at = Column(DateTime(timezone=True), server_default=func.now())
    latitude = Column(Float, nullable=True)
    longitude = Column(Float, nullable=True)
    reported_by = Column(String(100), nullable=True)

class Announcement(Base):
    __tablename__ = "announcements"

    id = Column(Integer, primary_key=True, index=True)
    title = Column(String(100), nullable=False)
    description = Column(String(255), nullable=False)
    category = Column(String(50), nullable=False)  # 'GENERAL', 'TRANSPORT', 'EVENT', 'EMERGENCY', 'ENERGY'
    is_important = Column(Boolean, default=False, nullable=False)
    photo_url = Column(String(255), nullable=True)
    created_at = Column(DateTime(timezone=True), server_default=func.now())
