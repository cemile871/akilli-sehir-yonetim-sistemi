package com.akillisehirapp.ui.profile

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.akillisehirapp.R
import com.akillisehirapp.data.api.RetrofitClient
import com.akillisehirapp.data.model.ProfileUpdateRequest
import com.akillisehirapp.databinding.FragmentProfileBinding
import com.akillisehirapp.ui.auth.LoginActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.InputStream

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val apiService by lazy { RetrofitClient.create(requireContext()) }
    
    private var selectedBase64Photo: String? = null
    private var dialogPreviewImageView: ImageView? = null

    // Galeriden resim seçici
    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val base64 = uriToBase64(requireContext(), it)
            if (base64 != null) {
                selectedBase64Photo = base64
                val bitmap = base64ToBitmap(base64)
                dialogPreviewImageView?.setImageBitmap(bitmap)
            } else {
                Toast.makeText(requireContext(), "Görsel işlenemedi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        loadUserInfo()
        setupNotificationPrefs()
        setupDarkMode()
        setupAccountButtons()
    }

    private fun loadUserInfo() {
        val userPrefs = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val isGuest = userPrefs.getBoolean("is_guest", false)

        if (isGuest) {
            binding.tvUserName.text = "Misafir Kullanıcı"
            binding.tvUserEmail.text = "Oturum: Misafir"
            binding.tvUserPhone.text = "Telefon: -"
            binding.tvUserAge.text = "Yaş: -"
            binding.tvAvatar.text = "M"
            binding.ivProfilePhoto.visibility = View.GONE
            binding.tvAvatar.visibility = View.VISIBLE
            
            // Misafir modunda kısıtlamalar
            binding.btnDeleteAccount.visibility = View.GONE
            binding.btnProfilDuzenle.visibility = View.GONE
        } else {
            binding.btnDeleteAccount.visibility = View.VISIBLE
            binding.btnProfilDuzenle.visibility = View.VISIBLE
            
            // API'den güncel kullanıcı bilgilerini çek
            lifecycleScope.launch {
                try {
                    val response = apiService.getMe()
                    if (response.isSuccessful && response.body() != null) {
                        val user = response.body()!!
                        
                        binding.tvUserName.text = user.name
                        binding.tvUserEmail.text = user.email
                        binding.tvUserPhone.text = "Telefon: ${user.phoneNumber ?: "-"}"
                        binding.tvUserAge.text = "Yaş: ${user.age ?: "-"}"
                        
                        // Profil Resmi Yükleme
                        if (!user.profilePhoto.isNullOrEmpty()) {
                            val bitmap = base64ToBitmap(user.profilePhoto)
                            if (bitmap != null) {
                                binding.ivProfilePhoto.setImageBitmap(bitmap)
                                binding.ivProfilePhoto.visibility = View.VISIBLE
                                binding.tvAvatar.visibility = View.GONE
                            } else {
                                setLetterAvatar(user.name)
                            }
                        } else {
                            setLetterAvatar(user.name)
                        }

                        // Eğer hesabı silme planlanmışsa uyaralım
                        if (user.deletionScheduled) {
                            Toast.makeText(requireContext(), "⚠️ Hesabınız 30 gün içinde silinecek şekilde işaretlenmiştir.", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        // Fallback local preferences
                        val name = userPrefs.getString("user_name", "Kullanıcı") ?: "Kullanıcı"
                        val email = userPrefs.getString("user_email", "") ?: ""
                        binding.tvUserName.text = name
                        binding.tvUserEmail.text = email
                        setLetterAvatar(name)
                    }
                } catch (e: Exception) {
                    // Fallback local preferences on failure
                    val name = userPrefs.getString("user_name", "Kullanıcı") ?: "Kullanıcı"
                    val email = userPrefs.getString("user_email", "") ?: ""
                    binding.tvUserName.text = name
                    binding.tvUserEmail.text = email
                    setLetterAvatar(name)
                }
            }
        }
    }

    private fun setLetterAvatar(name: String) {
        binding.tvAvatar.text = name.firstOrNull()?.uppercaseChar()?.toString() ?: "K"
        binding.ivProfilePhoto.visibility = View.GONE
        binding.tvAvatar.visibility = View.VISIBLE
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
        
        // Custom dialog layout inflator
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_profile_edit, null)
        val etName = dialogView.findViewById<TextInputEditText>(R.id.dialogEtName)
        val etPhone = dialogView.findViewById<TextInputEditText>(R.id.dialogEtPhone)
        val etAge = dialogView.findViewById<TextInputEditText>(R.id.dialogEtAge)
        val btnSelectPhoto = dialogView.findViewById<MaterialButton>(R.id.dialogBtnSelectPhoto)
        dialogPreviewImageView = dialogView.findViewById(R.id.dialogIvPreview)

        selectedBase64Photo = null // Reset selected photo

        // Mevcut verileri doldur
        lifecycleScope.launch {
            try {
                val response = apiService.getMe()
                if (response.isSuccessful && response.body() != null) {
                    val user = response.body()!!
                    etName.setText(user.name)
                    etPhone.setText(user.phoneNumber ?: "")
                    etAge.setText(user.age?.toString() ?: "")
                    
                    if (!user.profilePhoto.isNullOrEmpty()) {
                        selectedBase64Photo = user.profilePhoto
                        val bitmap = base64ToBitmap(user.profilePhoto)
                        dialogPreviewImageView?.setImageBitmap(bitmap)
                    }
                }
            } catch (e: Exception) {
                // Fallback to offline values if API fails
                etName.setText(userPrefs.getString("user_name", ""))
            }
        }

        btnSelectPhoto.setOnClickListener {
            // Galeri Intent'ini başlat
            selectImageLauncher.launch("image/*")
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Profili Düzenle")
            .setView(dialogView)
            .setPositiveButton("Kaydet") { _, _ ->
                val yeniAd = etName.text?.toString()?.trim() ?: ""
                val yeniTelefon = etPhone.text?.toString()?.trim() ?: ""
                val yeniYasStr = etAge.text?.toString()?.trim() ?: ""
                val yeniYas = yeniYasStr.toIntOrNull()

                if (yeniAd.isEmpty()) {
                    Toast.makeText(requireContext(), "Ad Soyad boş olamaz", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                lifecycleScope.launch {
                    try {
                        val request = ProfileUpdateRequest(
                            name = yeniAd,
                            phoneNumber = yeniTelefon.ifEmpty { null },
                            age = yeniYas,
                            profilePhoto = selectedBase64Photo
                        )
                        val response = apiService.updateProfile(request)
                        if (response.isSuccessful && response.body() != null) {
                            val user = response.body()!!
                            userPrefs.edit()
                                .putString("user_name", user.name)
                                .putString("user_email", user.email)
                                .apply()
                                
                            // Profil bilgilerini ekranda güncelle
                            loadUserInfo()
                            Toast.makeText(requireContext(), "Profil başarıyla güncellendi", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "Güncelleme başarısız: " + response.code(), Toast.LENGTH_LONG).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Bağlantı hatası: ${e.message}", Toast.LENGTH_LONG).show()
                    }
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
                .setMessage("Hesabınız 30 gün boyunca askıya alınacak ve bu sürenin sonunda kalıcı olarak silinecektir. Onaylıyor musunuz?")
                .setPositiveButton("Silmeyi Onayla") { _, _ ->
                    lifecycleScope.launch {
                        try {
                            val response = apiService.deleteAccountSchedule()
                            if (response.isSuccessful) {
                                Toast.makeText(requireContext(), "Hesabınız 30 gün içinde silinmek üzere sıraya alınmıştır.", Toast.LENGTH_LONG).show()
                                logout()
                            } else {
                                Toast.makeText(requireContext(), "Hata: " + response.code(), Toast.LENGTH_LONG).show()
                            }
                        } catch (e: Exception) {
                            Toast.makeText(requireContext(), "Bağlantı hatası: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                .setNegativeButton("İptal", null)
                .show()
        }
    }

    private fun logout() {
        requireContext().getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
            .edit().clear().apply()
        requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            .edit().clear().apply()
        startActivity(Intent(requireContext(), LoginActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    // ─── Yardımcı Dönüştürücü Metotlar ──────────────────────────────────────────
    private fun uriToBase64(context: Context, uri: Uri): String? {
        return try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()
            
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, outputStream)
            val bytes = outputStream.toByteArray()
            Base64.encodeToString(bytes, Base64.DEFAULT)
        } catch (e: Exception) {
            null
        }
    }

    private fun base64ToBitmap(base64Str: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(base64Str, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: Exception) {
            null
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
