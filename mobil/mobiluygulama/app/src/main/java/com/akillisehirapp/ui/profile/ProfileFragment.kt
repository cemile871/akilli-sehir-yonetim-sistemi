package com.akillisehirapp.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.akillisehirapp.R
import com.akillisehirapp.databinding.FragmentProfileBinding
import com.akillisehirapp.ui.auth.LoginActivity

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        loadUserInfo()
        setupNotificationPrefs()
        setupDarkMode()
        setupAccountButtons()
    }

    private fun loadUserInfo() {
        val prefs = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val name  = prefs.getString("user_name", "Kullanıcı") ?: "Kullanıcı"
        val email = prefs.getString("user_email", "kullanici@email.com") ?: ""
        binding.tvUserName.text = name
        binding.tvUserEmail.text = email
        binding.tvAvatar.text = name.firstOrNull()?.uppercaseChar()?.toString() ?: "K"
    }

    private fun setupNotificationPrefs() {
        val prefs = requireContext().getSharedPreferences("notif_prefs", Context.MODE_PRIVATE)
        listOf(
            Triple(binding.prefTraffic,    "Trafik Bildirimleri",         "notif_traffic"),
            Triple(binding.prefAirQuality, "Hava Kalitesi Bildirimleri",  "notif_air"),
            Triple(binding.prefEnergy,     "Enerji Kesinti Bildirimleri", "notif_energy"),
            Triple(binding.prefEmergency,  "Acil Durum Bildirimleri",     "notif_emergency")
        ).forEach { (pref, label, key) ->
            pref.tvPrefLabel.text = label
            pref.switchPref.isChecked = prefs.getBoolean(key, true)
            pref.switchPref.setOnCheckedChangeListener { _, checked ->
                prefs.edit().putBoolean(key, checked).apply()
            }
        }
    }

    private fun setupDarkMode() {
        val appPrefs = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

        // Gerçek gecelik moda bakarak switch'i ayarla
        val suankiMod = AppCompatDelegate.getDefaultNightMode()
        val isDark = when (suankiMod) {
            AppCompatDelegate.MODE_NIGHT_YES -> true
            AppCompatDelegate.MODE_NIGHT_NO  -> false
            else -> appPrefs.getBoolean("dark_mode", false)
        }

        binding.switchDarkMode.setOnCheckedChangeListener(null)
        binding.switchDarkMode.isChecked = isDark

        binding.switchDarkMode.setOnCheckedChangeListener { _, checked ->
            appPrefs.edit().putBoolean("dark_mode", checked).apply()
            AppCompatDelegate.setDefaultNightMode(
                if (checked) AppCompatDelegate.MODE_NIGHT_YES
                else         AppCompatDelegate.MODE_NIGHT_NO
            )
        }
    }

    private fun profilDuzenleDialogGoster() {
        val userPrefs = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val mevcutAd = userPrefs.getString("user_name", "") ?: ""

        val input = com.google.android.material.textfield.TextInputEditText(requireContext()).apply {
            setText(mevcutAd)
            hint = "Ad Soyad"
            setPadding(48, 24, 48, 24)
        }

        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Profili Düzenle")
            .setView(input)
            .setPositiveButton("Kaydet") { _, _ ->
                val yeniAd = input.text?.toString()?.trim() ?: ""
                if (yeniAd.isNotEmpty()) {
                    userPrefs.edit().putString("user_name", yeniAd).apply()
                    binding.tvUserName.text = yeniAd
                    binding.tvAvatar.text = yeniAd.firstOrNull()?.uppercaseChar()?.toString() ?: "K"
                    Toast.makeText(requireContext(), "Profil güncellendi", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("İptal", null)
            .show()
    }

    private fun setupAccountButtons() {
        binding.btnProfilDuzenle.setOnClickListener {
            profilDuzenleDialogGoster()
        }
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Çıkış Yap")
                .setMessage("Hesabınızdan çıkmak istiyor musunuz?")
                .setPositiveButton("Çıkış Yap") { _, _ -> logout() }
                .setNegativeButton("İptal", null)
                .show()
        }

        binding.btnDeleteAccount.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Hesabı Sil")
                .setMessage("Bu işlem geri alınamaz. Tüm verileriniz 30 gün içinde silinecektir.")
                .setPositiveButton("Sil") { _, _ -> logout() }
                .setNegativeButton("İptal", null)
                .show()
        }
    }

    private fun logout() {
        requireContext().getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
            .edit().clear().apply()
        startActivity(Intent(requireContext(), LoginActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
