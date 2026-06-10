package com.akillisehirapp.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.akillisehirapp.AkilliSehirApp
import com.akillisehirapp.ui.main.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * FCM Mesajlaşma Servisi.
 * Firebase bağımlılığı aktif edildiğinde (google-services plugin + firebase-messaging)
 * otomatik devreye girer. Şu an için stub olarak bırakıldı.
 */
class AkilliSehirMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // TODO: Token'ı backend'e gönder
        // RetrofitClient.create(this).updateFcmToken(mapOf("fcm_token" to token))
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val data   = message.data
        val baslik = data["baslik"] ?: message.notification?.title ?: "Akıllı Şehir"
        val icerik = data["aciklama"] ?: message.notification?.body ?: ""
        val tip    = data["tip"] ?: "GENEL"

        val kanalId = when (tip) {
            "ACIL_DURUM"     -> AkilliSehirApp.CHANNEL_EMERGENCY
            "ENERJI_KESINTI" -> AkilliSehirApp.CHANNEL_ENERGY
            else             -> AkilliSehirApp.CHANNEL_GENERAL
        }

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )

        val bildirim = NotificationCompat.Builder(this, kanalId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(baslik)
            .setContentText(icerik)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(
                if (tip == "ACIL_DURUM") NotificationCompat.PRIORITY_MAX
                else NotificationCompat.PRIORITY_DEFAULT
            )
            .build()

        val nm = getSystemService(NotificationManager::class.java)
        nm.notify(System.currentTimeMillis().toInt(), bildirim)
    }
}
