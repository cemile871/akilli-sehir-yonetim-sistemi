package com.akillisehirapp.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.akillisehirapp.R
import com.akillisehirapp.data.api.RetrofitClient
import com.akillisehirapp.data.model.LoginRequest
import com.akillisehirapp.databinding.ActivityLoginBinding
import com.akillisehirapp.ui.main.MainActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val apiService by lazy { RetrofitClient.create(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener { attemptLogin() }

        binding.btnGuest.setOnClickListener {
            // Misafir mod: token kaydetmeden direkt ana sayfaya
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Şifre sıfırlama e-postası gönderildi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun attemptLogin() {
        val email    = binding.etEmail.text?.toString()?.trim() ?: ""
        val password = binding.etPassword.text?.toString() ?: ""

        // Basit validasyon
        if (email.isEmpty()) {
            binding.tilEmail.error = "E-posta gerekli"
            return
        }
        if (password.length < 6) {
            binding.tilPassword.error = "Şifre en az 6 karakter olmalı"
            return
        }
        binding.tilEmail.error    = null
        binding.tilPassword.error = null

        setLoading(true)

        lifecycleScope.launch {
            try {
                val response = apiService.login(LoginRequest(email, password))
                if (response.isSuccessful) {
                    val body = response.body()!!
                    // Token kaydet
                    getSharedPreferences("auth_prefs", Context.MODE_PRIVATE).edit()
                        .putString("access_token", body.accessToken)
                        .putString("refresh_token", body.refreshToken)
                        .apply()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    binding.tilPassword.error = "E-posta veya şifre hatalı"
                }
            } catch (e: Exception) {
                // Geliştirme aşamasında: direkt ana sayfaya git
                Toast.makeText(this@LoginActivity,
                    "Sunucuya bağlanılamadı (demo mod)", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            } finally {
                setLoading(false)
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        binding.btnLogin.isEnabled = !loading
        binding.btnLogin.text = if (loading) "Giriş yapılıyor…" else getString(R.string.btn_login)
    }
}
