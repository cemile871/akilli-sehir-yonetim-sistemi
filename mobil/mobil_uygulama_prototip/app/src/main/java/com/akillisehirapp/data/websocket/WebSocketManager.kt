package com.akillisehirapp.data.websocket

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.util.concurrent.TimeUnit

data class SunucuMesaji(
    val tip: String,
    val data: Map<String, Any>?
)

enum class BaglantiDurumu { BAGLI, BAGLANIYOR, KOPTU }

class WebSocketManager(private val context: Context) {

    companion object {
        private const val TAG = "WebSocket"
        private const val WS_URL = "wss://api.akillisehir.belediye.gov.tr/ws"
    }

    private val client = OkHttpClient.Builder()
        .readTimeout(0, TimeUnit.MILLISECONDS)
        .build()
    private val gson = Gson()
    private var webSocket: WebSocket? = null
    private val handler = Handler(Looper.getMainLooper())

    private val _mesajlar = MutableLiveData<SunucuMesaji>()
    val mesajlar: LiveData<SunucuMesaji> = _mesajlar

    private val _durum = MutableLiveData(BaglantiDurumu.KOPTU)
    val durum: LiveData<BaglantiDurumu> = _durum

    fun baglan() {
        val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        val token = prefs.getString("access_token", "") ?: ""

        _durum.postValue(BaglantiDurumu.BAGLANIYOR)

        val istek = Request.Builder()
            .url("$WS_URL?token=$token")
            .build()

        webSocket = client.newWebSocket(istek, object : WebSocketListener() {
            override fun onOpen(ws: WebSocket, response: Response) {
                Log.d(TAG, "Bağlandı")
                _durum.postValue(BaglantiDurumu.BAGLI)
            }

            override fun onMessage(ws: WebSocket, text: String) {
                try {
                    val mesaj = gson.fromJson(text, SunucuMesaji::class.java)
                    _mesajlar.postValue(mesaj)
                } catch (e: Exception) {
                    Log.e(TAG, "Mesaj parse hatası: ${e.message}")
                }
            }

            override fun onFailure(ws: WebSocket, t: Throwable, response: Response?) {
                Log.e(TAG, "Bağlantı hatası: ${t.message}")
                _durum.postValue(BaglantiDurumu.KOPTU)
                // 5 saniye sonra yeniden bağlan
                handler.postDelayed({ baglan() }, 5000)
            }

            override fun onClosed(ws: WebSocket, code: Int, reason: String) {
                _durum.postValue(BaglantiDurumu.KOPTU)
            }
        })
    }

    fun kes() {
        handler.removeCallbacksAndMessages(null)
        webSocket?.close(1000, "Kullanıcı çıkışı")
        webSocket = null
    }
}
