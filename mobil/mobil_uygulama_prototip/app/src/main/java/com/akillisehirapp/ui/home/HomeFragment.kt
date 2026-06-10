package com.akillisehirapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.akillisehirapp.R
import com.akillisehirapp.data.model.Announcement
import com.akillisehirapp.data.model.AnnouncementCategory
import com.akillisehirapp.databinding.FragmentHomeBinding
import com.akillisehirapp.ui.announcements.AnnouncementAdapter
import androidx.core.content.ContextCompat
import android.os.Handler
import android.os.Looper

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val handler = Handler(Looper.getMainLooper())
    private val havaGuncelle = object : Runnable {
        override fun run() {
            val aqi  = (30..180).random()
            val pm25 = (10..80).random()
            val co2  = (380..520).random()
            val no2  = (10..60).random()

            val (seviye, renk, oneri) = when {
                aqi < 50  -> Triple("İyi",      R.color.aqi_good,      "Hava temiz, dışarı çıkabilirsiniz.")
                aqi < 100 -> Triple("Orta",     R.color.aqi_moderate,  "Hassas gruplar dışarıyı sınırlasın.")
                aqi < 150 -> Triple("Kötü",     R.color.aqi_unhealthy, "Dışarıda yoğun aktiviteden kaçının.")
                else      -> Triple("Tehlikeli",R.color.aqi_hazardous, "Dışarı çıkmayın, maske takın.")
            }

            val b = _binding ?: return
            b.tvAqiScore.text  = aqi.toString()
            b.tvAqiLevel.text  = seviye
            b.tvAqiScore.setTextColor(ContextCompat.getColor(requireContext(), renk))
            b.tvAqiAdvice.text = oneri
            b.chipPm25.text    = "PM2.5: $pm25"
            b.chipCo2.text     = "CO2: $co2"
            b.chipNo2.text     = "NO2: $no2"

            handler.postDelayed(this, 10_000)
        }
    }

    override fun onResume() {
        super.onResume()
        handler.post(havaGuncelle)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(havaGuncelle)
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        loadDemoData()

        binding.btnTrafficMap.setOnClickListener {
            requireActivity().findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(
                R.id.bottomNavigationView
            ).selectedItemId = R.id.mapFragment
        }

        binding.tvSeeAllAnnouncements.setOnClickListener {
            requireActivity().findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(
                R.id.bottomNavigationView
            ).selectedItemId = R.id.announcementsFragment
        }

    }

    private fun loadDemoData() {
        val demoAnnouncements = listOf(
            Announcement(1, "Çarşı Kavşağı yol çalışması",
                "Pazar günü 08:00-17:00 arası trafiğe kapalı.",
                AnnouncementCategory.TRANSPORT, false, "2 saat önce"),
            Announcement(2, "Enerji tasarruf kampanyası",
                "Bu ay katılımcılara fatura indirimi.",
                AnnouncementCategory.ENERGY, false, "5 saat önce"),
            Announcement(3, "Bahar festivali",
                "Merkez parkta cumartesi saat 10:00'da.",
                AnnouncementCategory.EVENT, false, "1 gün önce")
        )

        binding.rvAnnouncements.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = AnnouncementAdapter(demoAnnouncements) { /* detay */ }
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
