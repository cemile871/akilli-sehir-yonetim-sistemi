package com.akillisehirapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akillisehirapp.R
import com.akillisehirapp.data.model.Announcement
import com.akillisehirapp.data.model.AnnouncementCategory
import com.akillisehirapp.databinding.FragmentHomeBinding
import com.akillisehirapp.ui.announcements.AnnouncementAdapter
// import kaldırıldı

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        // Demo veriler
        loadDemoData()

        binding.btnTrafficMap.setOnClickListener {
            // Bottom nav'ı Map'e geçir
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
        // Duyurular listesi (demo)
        val demoAnnouncements = listOf(
            Announcement(1, "Çarşı Kavşağı yol çalışması", "Pazar günü 08:00-17:00 arası trafiğe kapalı.",
                AnnouncementCategory.TRANSPORT, false, "2 saat önce"),
            Announcement(2, "Enerji tasarruf kampanyası", "Bu ay katılımcılara indirim.",
                AnnouncementCategory.ENERGY, false, "5 saat önce"),
            Announcement(3, "Bahar festivali", "Merkez parkta cumartesi saat 10:00'da.",
                AnnouncementCategory.EVENT, false, "1 gün önce")
        )

        binding.rvAnnouncements.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = AnnouncementAdapter(demoAnnouncements) { /* detay */ }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
