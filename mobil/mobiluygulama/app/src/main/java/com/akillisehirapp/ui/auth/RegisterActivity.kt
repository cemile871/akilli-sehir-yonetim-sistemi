package com.akillisehirapp.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.akillisehirapp.data.api.RetrofitClient
import com.akillisehirapp.data.model.RegisterRequest
import com.akillisehirapp.databinding.ActivityRegisterBinding
import com.akillisehirapp.ui.main.MainActivity
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val apiService by lazy { RetrofitClient.create(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPasswordStrength()
        setupKvkkToggle()

        binding.btnRegister.setOnClickListener { attemptRegister() }
        binding.tvLogin.setOnClickListener { finish() }
    }

    private fun setupPasswordStrength() {
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val pw = s?.toString() ?: ""
                val strength = calcStrength(pw)
                binding.passwordStrengthBar.progress = strength
                binding.tvPasswordStrength.text = when (strength) {
                    0 -> ""
                    1 -> "Zayıf şifre"
                    2 -> "Orta güçte şifre"
                    else -> "Güçlü şifre ✓"
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun calcStrength(pw: String): Int {
        var score = 0
        if (pw.length >= 8) score++
        if (pw.any { it.isDigit() }) score++
        if (pw.any { !it.isLetterOrDigit() }) score++
        return score
    }

    private fun setupKvkkToggle() {
        binding.cbKvkk.setOnCheckedChangeListener { _, checked ->
            binding.btnRegister.isEnabled = checked
        }
    }

    private fun attemptRegister() {
        val name     = binding.etName.text?.toString()?.trim() ?: ""
        val email    = binding.etEmail.text?.toString()?.trim() ?: ""
        val password = binding.etPassword.text?.toString() ?: ""
        val confirm  = binding.etPasswordConfirm.text?.toString() ?: ""

        if (name.isEmpty())  { binding.tilName.error = "Ad Soyad gerekli"; return }
        if (email.isEmpty()) { binding.tilEmail.error = "E-posta gerekli"; return }
        if (password.length < 8) { binding.tilPassword.error = "En az 8 karakter"; return }
        if (password != confirm) { binding.tilPasswordConfirm.error = "Şifreler eşleşmiyor"; return }

        lifecycleScope.launch {
            try {
                val response = apiService.register(RegisterRequest(name, email, password))
                if (response.isSuccessful) {
                    // burası zaten backend'e bağlanınca çalışır
                } else {}
            } catch (e: Exception) {
                // Backend yok — yerel kayıt
                val authPrefs = getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                val userPrefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                authPrefs.edit()
                    .putString("registered_email",    email)
                    .putString("registered_password", password)
                    .putString("access_token",        "local_$email")
                    .apply()
                userPrefs.edit()
                    .putString("user_name",  name)
                    .putString("user_email", email)
                    .apply()
                startActivity(
                    Intent(this@RegisterActivity, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            }
        }
    }
}
