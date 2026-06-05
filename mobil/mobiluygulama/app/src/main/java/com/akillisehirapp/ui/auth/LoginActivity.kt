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

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Geçerli bir e-posta girin"
            return
        }
        if (password.length < 6) {
            binding.tilPassword.error = "Şifre en az 6 karakter olmalı"
            return
        }
        binding.tilEmail.error    = null
        binding.tilPassword.error = null
        setLoading(true)

        val authPrefs  = getSharedPreferences("auth_prefs",  Context.MODE_PRIVATE)
        val userPrefs  = getSharedPreferences("user_prefs",  Context.MODE_PRIVATE)
        val savedEmail = authPrefs.getString("registered_email",    null)
        val savedPass  = authPrefs.getString("registered_password", null)

        when {
            // Kayıtlı kullanıcı eşleşiyor
            savedEmail != null && email == savedEmail && password == savedPass -> {
                authPrefs.edit().putString("access_token", "local_$email").apply()
                goMain()
            }
            // Kayıtlı kullanıcı var ama şifre yanlış
            savedEmail != null && email == savedEmail -> {
                binding.tilPassword.error = "Şifre hatalı"
                setLoading(false)
            }
            // Hiç kayıt yok → demo mod (ilk açılışta direkt gir)
            savedEmail == null -> {
                authPrefs.edit()
                    .putString("access_token",  "demo_$email")
                    .putString("user_email",    email)
                    .apply()
                userPrefs.edit()
                    .putString("user_email",    email)
                    .putString("user_name",     email.substringBefore("@"))
                    .apply()
                goMain()
            }
            else -> {
                binding.tilEmail.error = "Bu e-posta kayıtlı değil"
                setLoading(false)
            }
        }
    }

    private fun goMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun setLoading(loading: Boolean) {
        binding.btnLogin.isEnabled = !loading
        binding.btnLogin.text = if (loading) "Giriş yapılıyor…" else getString(R.string.btn_login)
    }
}
