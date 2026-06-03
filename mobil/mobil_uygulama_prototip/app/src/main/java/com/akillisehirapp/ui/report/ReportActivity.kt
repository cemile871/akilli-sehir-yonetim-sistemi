package com.akillisehirapp.ui.report

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.akillisehirapp.R

/**
 * Aşama 2'de (Hafta 5) tam olarak geliştirilecektir.
 * Şu anda yalnızca yer tutucu ekran gösterir.
 */
class ReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(android.R.layout.simple_list_item_1)
        findViewById<TextView>(android.R.id.text1).apply {
            text = "Olay Bildir — Aşama 2'de tamamlanacak"
            textSize = 18f
            setPadding(48, 48, 48, 48)
        }
    }
}
