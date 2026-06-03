package com.akillisehirapp.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
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
        binding.prefTraffic.tvPrefLabel.text = "Trafik Bildirimleri"
        binding.prefTraffic.switchPref.isChecked = prefs.getBoolean("notif_traffic", true)
        binding.prefTraffic.switchPref.setOnCheckedChangeListener { _, checked ->
            prefs.edit().putBoolean("notif_traffic", checked).apply()
        }
    }

    private fun setupDarkMode() {
        val isDark = AppCompatDelegate.getDefaultNightMode() ==
            AppCompatDelegate.MODE_NIGHT_YES
        binding.switchDarkMode.isChecked = isDark
        binding.switchDarkMode.setOnCheckedChangeListener { _, checked ->
            AppCompatDelegate.setDefaultNightMode(
                if (checked) AppCompatDelegate.MODE_NIGHT_YES
                else         AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            )
        }
    }

    private fun setupAccountButtons() {
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
