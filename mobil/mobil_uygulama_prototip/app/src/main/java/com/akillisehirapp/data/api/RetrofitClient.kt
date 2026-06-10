package com.akillisehirapp.data.api

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    // TODO: Backend URL'ini buraya yaz
    private const val BASE_URL = "http://10.0.2.2:8000/api/v1/"

    private fun buildClient(context: Context): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Token ekleme interceptor'ı
        val authInterceptor = Interceptor { chain ->
            val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
            val token = prefs.getString("access_token", null)
            val request = if (token != null) {
                chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            } else {
                chain.request()
            }
            chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    fun create(context: Context): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(buildClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
