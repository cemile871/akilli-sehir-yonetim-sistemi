from pydantic import BaseModel, Field
from typing import List, Dict, Any, Optional
from datetime import datetime

# Auth Schemas
class UserLogin(BaseModel):
    email: str = Field(..., example="admin@belediye.gov")
    password: str = Field(..., example="123456")

class Token(BaseModel):
    access_token: str
    token_type: str
    role: str

# Sensor Schemas
class SensorBase(BaseModel):
    category: str  # 'traffic_camera', 'environment_sensor'
    name: str      # e.g., 'Çarşı Kavşağı'
    latitude: float
    longitude: float

class SensorCreate(SensorBase):
    pass

class SensorResponse(SensorBase):
    id: str
    created_at: datetime

    class Config:
        from_attributes = True

# Sensor Reading Schemas
class SensorReadingBase(BaseModel):
    sensor_id: str
    metrics: Dict[str, Any]
    is_anomaly: bool = False

class SensorReadingCreate(SensorReadingBase):
    pass

class SensorReadingResponse(SensorReadingBase):
    id: int
    recorded_at: datetime
    sensor: Optional[SensorResponse] = None

    class Config:
        from_attributes = True


class SensorDataListResponse(BaseModel):
    status: str
    data: List[SensorReadingResponse]
    pagination: Dict[str, int]

# ML Traffic Optimization Schemas
class TrafficOptimizeRequest(BaseModel):
    region_id: str = Field(..., example="bosphorus_bridge_zone")
    time_horizon_minutes: int = Field(60, example=60)

class TrafficPrediction(BaseModel):
    expected_congestion_level: str  # 'Düşük', 'Orta', 'Yüksek'
    peak_time: str                  # e.g., '18:15'

class TrafficOptimizationAction(BaseModel):
    suggested_green_light_duration: int  # Saniye
    confidence_score: float

class TrafficOptimizeResponse(BaseModel):
    prediction: TrafficPrediction
    optimization_action: TrafficOptimizationAction

# Incident Schemas
class IncidentBase(BaseModel):
    title: str
    description: str
    category: str  # 'traffic', 'air_quality', 'noise'
    status: str = "active"
    latitude: Optional[float] = None
    longitude: Optional[float] = None
    reported_by: Optional[str] = None

class IncidentResponse(IncidentBase):
    id: str
    reported_at: datetime
    response_time_seconds: Optional[int] = None

    class Config:
        from_attributes = True

class IncidentUpdate(BaseModel):
    status: str = Field(..., example="resolved")
    response_time_seconds: Optional[int] = Field(None, example=450)

# Mobile App Compatibility Schemas
class RegisterRequest(BaseModel):
    name: str
    email: str
    password: str

class UserResponse(BaseModel):
    id: str
    name: str
    email: str
    role: str = "citizen"

class AuthResponse(BaseModel):
    access_token: str
    refresh_token: str
    user: UserResponse

class TrafficDataMobile(BaseModel):
    sensorId: str
    vehicleCount: int
    avgSpeed: float
    roadStatus: str  # 'normal', 'accident', 'closed'
    densityLevel: str  # 'FREE', 'MODERATE', 'HEAVY', 'CONGESTED'
    timestamp: str

class AirQualityDataMobile(BaseModel):
    aqiScore: int
    pm25: float
    pm10: float
    co2: float
    no2: float
    healthAdvice: str
    timestamp: str

class IncidentMobile(BaseModel):
    id: int
    kategori: str
    aciklama: str
    enlem: float
    boylam: float
    status: str  # 'pending', 'acknowledged', 'resolved'
    reportedBy: Optional[str] = None
    photoUrls: List[str] = []
    createdAt: str
    distanceKm: Optional[float] = None

class AnnouncementResponse(BaseModel):
    id: int
    title: str
    description: str
    category: str  # 'GENERAL', 'TRANSPORT', 'EVENT', 'EMERGENCY', 'ENERGY'
    isImportant: bool
    createdAt: str
    photoUrl: Optional[str] = None

class BildirimYaniti(BaseModel):
    id: int
    takip_no: str


