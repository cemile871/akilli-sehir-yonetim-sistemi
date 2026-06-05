package com.akillisehirapp.data.api

import com.akillisehirapp.data.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // ─── Auth ─────────────────────────────────────────────────────────────────
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

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
        @Query("page") page: Int = 1
    ): Response<List<Announcement>>

    // ─── Olaylar (Aşama 2) ───────────────────────────────────────────────────
    @GET("incidents/active")
    suspend fun getActiveIncidents(): Response<List<Incident>>

    @Multipart
    @POST("incidents")
    suspend fun bildirimGonder(
        @Part("kategori")    kategori: RequestBody,
        @Part("aciklama")    aciklama: RequestBody,
        @Part("enlem")       enlem:    RequestBody,
        @Part("boylam")      boylam:   RequestBody,
        @Part("reported_by") reportedBy: RequestBody?,
        @Part fotograflar: List<MultipartBody.Part>
    ): Response<BildirimYaniti>

    // ─── FCM Token ────────────────────────────────────────────────────────────
    @PUT("users/fcm-token")
    suspend fun updateFcmToken(@Body body: Map<String, String>): Response<Unit>
}
