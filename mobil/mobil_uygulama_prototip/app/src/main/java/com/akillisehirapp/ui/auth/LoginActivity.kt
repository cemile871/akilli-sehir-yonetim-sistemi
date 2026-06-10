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
            // Misafir mod: SharedPreferences'a misafir kaydı düşüp direkt ana sayfaya git
            val userPrefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val authPrefs = getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
            
            authPrefs.edit().clear().apply() // Tokenı temizle
            userPrefs.edit()
                .putBoolean("is_guest", true)
                .putString("user_name", "Misafir Kullanıcı")
                .putString("user_email", "")
                .apply()
                
            goMain()
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

        if (email.isEmpty()) {
            binding.tilEmail.error = "Kullanıcı adı veya e-posta girin"
            return
        }
        if (password.length < 4) {
            binding.tilPassword.error = "Şifre en az 4 karakter olmalı"
            return
        }
        binding.tilEmail.error    = null
        binding.tilPassword.error = null
        setLoading(true)

        lifecycleScope.launch {
            try {
                val response = apiService.login(LoginRequest(email, password))
                if (response.isSuccessful && response.body() != null) {
                    val authData = response.body()!!
                    
                    val authPrefs = getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                    val userPrefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                    
                    authPrefs.edit()
                        .putString("access_token", authData.accessToken)
                        .putString("user_email", authData.user.email)
                        .apply()
                        
                    userPrefs.edit()
                        .putBoolean("is_guest", false)
                        .putString("user_name", authData.user.name)
                        .putString("user_email", authData.user.email)
                        .apply()
                        
                    goMain()
                } else {
                    val errorMsg = if (response.code() == 401) "Kullanıcı adı veya şifre hatalı" else "Giriş başarısız: " + response.code()
                    Toast.makeText(this@LoginActivity, errorMsg, Toast.LENGTH_LONG).show()
                    setLoading(false)
                }
            } catch (e: Exception) {
                Toast.makeText(this@LoginActivity, "Bağlantı hatası: ${e.message}", Toast.LENGTH_LONG).show()
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
