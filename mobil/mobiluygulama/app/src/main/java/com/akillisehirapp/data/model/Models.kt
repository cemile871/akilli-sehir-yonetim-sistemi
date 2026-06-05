package com.akillisehirapp.data.model

import com.google.gson.annotations.SerializedName

// ─── Auth ────────────────────────────────────────────────────────────────────
data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

data class AuthResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    val user: User
)

data class User(
    val id: String,
    val name: String,
    val email: String,
    val role: String = "citizen"
)

// ─── Trafik ──────────────────────────────────────────────────────────────────
data class TrafficData(
    val sensorId: String,
    val vehicleCount: Int,
    val avgSpeed: Double,
    val roadStatus: String,   // normal / accident / closed
    val densityLevel: DensityLevel,
    val timestamp: String
)

enum class DensityLevel { FREE, MODERATE, HEAVY, CONGESTED }

// ─── Hava Kalitesi ────────────────────────────────────────────────────────────
data class AirQualityData(
    val aqiScore: Int,
    val pm25: Double,
    val pm10: Double,
    val co2: Double,
    val no2: Double,
    val healthAdvice: String,
    val timestamp: String
)

// ─── Olay / Incident ─────────────────────────────────────────────────────────
data class Incident(
    val id: Int,
    val kategori: String,
    val aciklama: String,
    val enlem: Double,
    val boylam: Double,
    val status: String,       // pending / acknowledged / resolved
    val reportedBy: String?,
    val photoUrls: List<String>,
    val createdAt: String,
    val distanceKm: Double? = null
)

// ─── Duyuru ──────────────────────────────────────────────────────────────────
data class Announcement(
    val id: Int,
    val title: String,
    val description: String,
    val category: AnnouncementCategory,
    val isImportant: Boolean,
    val createdAt: String,
    val photoUrl: String? = null,
    val photoUris: List<String> = emptyList()
)

enum class AnnouncementCategory(val label: String) {
    GENERAL("Genel"),
    TRANSPORT("Ulaşım"),
    EVENT("Etkinlik"),
    EMERGENCY("Acil"),
    ENERGY("Enerji")
}

// ─── Enerji ──────────────────────────────────────────────────────────────────
data class EnergyData(
    val sensorId: String,
    val consumption: Double,
    val region: String,
    val timestamp: String
)

// ─── API Genel Yanıtlar ───────────────────────────────────────────────────────
data class ApiResponse<T>(
    val success: Boolean,
    val data: T?,
    val message: String?
)

// ─── Bildirim Yanıtı (Aşama 2) ───────────────────────────────────────────────
data class BildirimYaniti(
    val id: Int,
    @SerializedName("takip_no") val takipNo: String
)
