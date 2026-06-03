package com.akillisehirapp.ui.announcements

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akillisehirapp.R
import com.akillisehirapp.data.model.Announcement
import com.akillisehirapp.data.model.AnnouncementCategory
import com.akillisehirapp.databinding.FragmentAnnouncementsBinding
import com.akillisehirapp.databinding.ItemAnnouncementBinding
import android.view.LayoutInflater

class AnnouncementsFragment : Fragment(R.layout.fragment_announcements) {

    private var _binding: FragmentAnnouncementsBinding? = null
    private val binding get() = _binding!!

    private val allAnnouncements = listOf(
        Announcement(1, "Çarşı Kavşağı yol çalışması",
            "Pazar günü 08:00-17:00 arası trafiğe kapalı. Alternatif güzergahları kullanınız.",
            AnnouncementCategory.TRANSPORT, false, "2 saat önce"),
        Announcement(2, "Acil: Su kesintisi",
            "Merkez Mahallesi'nde boru patlaması nedeniyle 3-4 saat su verilemeyecektir.",
            AnnouncementCategory.EMERGENCY, true, "3 saat önce"),
        Announcement(3, "Enerji tasarruf kampanyası",
            "Bu ay katılımcı hanelere fatura indirimli uygulanacaktır.",
            AnnouncementCategory.ENERGY, false, "5 saat önce"),
        Announcement(4, "Bahar festivali",
            "Merkez parkta cumartesi saat 10:00'da. Tüm halk davetlidir.",
            AnnouncementCategory.EVENT, false, "1 gün önce"),
        Announcement(5, "Yeni park alanı açılıyor",
            "Üniversite Kavşağı yakınında yeni yeşil alan Cuma günü açılıyor.",
            AnnouncementCategory.GENERAL, false, "2 gün önce")
    )

    private lateinit var adapter: AnnouncementAdapter
    private var selectedCategory: AnnouncementCategory? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAnnouncementsBinding.bind(view)

        adapter = AnnouncementAdapter(allAnnouncements) { announcement ->
            // TODO: detay sayfasına git
        }

        binding.rvAnnouncements.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAnnouncements.adapter = adapter

        setupFilters()
    }

    private fun setupFilters() {
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            selectedCategory = when {
                checkedIds.contains(R.id.chipTransport)  -> AnnouncementCategory.TRANSPORT
                checkedIds.contains(R.id.chipEvent)      -> AnnouncementCategory.EVENT
                checkedIds.contains(R.id.chipEmergency)  -> AnnouncementCategory.EMERGENCY
                checkedIds.contains(R.id.chipGeneral)    -> AnnouncementCategory.GENERAL
                else                                      -> null
            }
            filterAnnouncements()
        }
    }

    private fun filterAnnouncements() {
        val filtered = if (selectedCategory == null) allAnnouncements
        else allAnnouncements.filter { it.category == selectedCategory }

        adapter.submitList(filtered)
        binding.emptyState.visibility = if (filtered.isEmpty()) View.VISIBLE else View.GONE
        binding.rvAnnouncements.visibility = if (filtered.isEmpty()) View.GONE else View.VISIBLE
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}

class AnnouncementAdapter(
    private var items: List<Announcement>,
    private val onClick: (Announcement) -> Unit
) : RecyclerView.Adapter<AnnouncementAdapter.VH>() {

    inner class VH(private val binding: ItemAnnouncementBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Announcement) {
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            binding.tvDate.text = item.createdAt
            binding.chipCategory.text = item.category.label
            binding.root.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemAnnouncementBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size

    fun submitList(newItems: List<Announcement>) {
        items = newItems
        notifyDataSetChanged()
    }
}
