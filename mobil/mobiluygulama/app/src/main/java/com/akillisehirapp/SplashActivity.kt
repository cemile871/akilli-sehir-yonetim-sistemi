package com.akillisehirapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.akillisehirapp.ui.auth.LoginActivity
import com.akillisehirapp.ui.main.MainActivity
import com.akillisehirapp.ui.onboarding.OnboardingActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            navigateNext()
        }, 1500)
    }

    private fun navigateNext() {
        val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val onboardingDone = prefs.getBoolean("onboarding_done", false)
        val token = getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
            .getString("access_token", null)

        val nextActivity = when {
            !onboardingDone       -> OnboardingActivity::class.java
            token != null         -> MainActivity::class.java
            else                  -> LoginActivity::class.java
        }

        startActivity(Intent(this, nextActivity))
        finish()
    }
}
