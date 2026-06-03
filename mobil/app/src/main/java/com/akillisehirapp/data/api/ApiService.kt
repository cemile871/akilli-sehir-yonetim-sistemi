package com.akillisehirapp.data.api

import com.akillisehirapp.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // ─── Auth ─────────────────────────────────────────────────────────────────
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    @POST("auth/logout")
    suspend fun logout(): Response<Unit>

    // ─── Trafik ───────────────────────────────────────────────────────────────
    @GET("traffic/current")
    suspend fun getCurrentTraffic(): Response<List<TrafficData>>

    // ─── Hava Kalitesi ────────────────────────────────────────────────────────
    @GET("air-quality/current")
    suspend fun getAirQuality(): Response<AirQualityData>

    // ─── Duyurular ────────────────────────────────────────────────────────────
    @GET("announcements")
    suspend fun getAnnouncements(
        @Query("category") category: String? = null,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<List<Announcement>>

    @GET("announcements/{id}")
    suspend fun getAnnouncementDetail(@Path("id") id: Int): Response<Announcement>

    // ─── Olaylar ──────────────────────────────────────────────────────────────
    @GET("incidents/active")
    suspend fun getActiveIncidents(): Response<List<Incident>>

    // ─── FCM Token ────────────────────────────────────────────────────────────
    @PUT("users/fcm-token")
    suspend fun updateFcmToken(@Body body: Map<String, String>): Response<Unit>
}
