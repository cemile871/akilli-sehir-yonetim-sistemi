package com.akillisehirapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class AkilliSehirApp : Application() {

    companion object {
        const val CHANNEL_EMERGENCY  = "kanal_acil"
        const val CHANNEL_ENERGY     = "kanal_enerji"
        const val CHANNEL_GENERAL    = "kanal_genel"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = getSystemService(NotificationManager::class.java)

            nm.createNotificationChannel(
                NotificationChannel(CHANNEL_EMERGENCY, "Acil Durum",
                    NotificationManager.IMPORTANCE_HIGH).apply {
                    description = "Trafik kazası, yangın, sel gibi kritik olaylar"
                    enableVibration(true)
                    vibrationPattern = longArrayOf(0, 500, 200, 500)
                }
            )
            nm.createNotificationChannel(
                NotificationChannel(CHANNEL_ENERGY, "Enerji Kesintileri",
                    NotificationManager.IMPORTANCE_DEFAULT).apply {
                    description = "Planlı ve acil enerji kesintileri"
                }
            )
            nm.createNotificationChannel(
                NotificationChannel(CHANNEL_GENERAL, "Belediye Duyuruları",
                    NotificationManager.IMPORTANCE_LOW).apply {
                    description = "Genel belediye haberleri"
                }
            )
        }
    }
}
