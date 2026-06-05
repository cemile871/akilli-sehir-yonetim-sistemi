package com.akillisehirapp.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.akillisehirapp.R
import com.akillisehirapp.databinding.ActivityOnboardingBinding
import com.akillisehirapp.ui.auth.LoginActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

data class OnboardingPage(
    val title: String,
    val description: String,
    val imageRes: Int
)

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    private val pages = listOf(
        OnboardingPage(
            "Şehrinizin Nabzını Tutun",
            "Trafik, hava kalitesi ve enerji verilerine tek noktadan erişin.",
            android.R.drawable.ic_dialog_map
        ),
        OnboardingPage(
            "Anlık Bildirimler",
            "Acil durum ve planlı kesinti bildirimlerinden ilk siz haberdar olun.",
            android.R.drawable.ic_dialog_alert
        ),
        OnboardingPage(
            "Belediyeye Ulaşın",
            "Sorunları bildirin, şehri birlikte güzelleştirelim.",
            android.R.drawable.ic_input_add
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = OnboardingAdapter(pages)
        binding.viewPager.adapter = adapter

        // Dot indikatörleri
        setupIndicators()
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateIndicators(position)
                val isLast = position == pages.size - 1
                binding.btnNext.text = if (isLast) getString(R.string.btn_start)
                                       else getString(R.string.btn_next)
                binding.btnSkip.visibility = if (isLast) View.INVISIBLE else View.VISIBLE
            }
        })

        binding.btnNext.setOnClickListener {
            val current = binding.viewPager.currentItem
            if (current < pages.size - 1) {
                binding.viewPager.currentItem = current + 1
            } else {
                finishOnboarding()
            }
        }

        binding.btnSkip.setOnClickListener { finishOnboarding() }
    }

    private fun setupIndicators() {
        pages.forEach { _ ->
            val dot = View(this).apply {
                val size = 10
                val params = ViewGroup.MarginLayoutParams(size.dpToPx(), size.dpToPx())
                params.marginStart = 4.dpToPx()
                params.marginEnd = 4.dpToPx()
                layoutParams = params
                setBackgroundResource(android.R.drawable.btn_radio)
            }
            binding.indicatorLayout.addView(dot)
        }
        updateIndicators(0)
    }

    private fun updateIndicators(position: Int) {
        for (i in 0 until binding.indicatorLayout.childCount) {
            val dot = binding.indicatorLayout.getChildAt(i)
            dot.alpha = if (i == position) 1f else 0.3f
        }
    }

    private fun finishOnboarding() {
        getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            .edit().putBoolean("onboarding_done", true).apply()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun Int.dpToPx(): Int =
        (this * resources.displayMetrics.density + 0.5f).toInt()
}

class OnboardingAdapter(private val pages: List<OnboardingPage>) :
    RecyclerView.Adapter<OnboardingAdapter.PageVH>() {

    inner class PageVH(view: View) : RecyclerView.ViewHolder(view) {
        val ivIllustration: ImageView = view.findViewById(R.id.ivIllustration)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_onboarding_page, parent, false)
        return PageVH(view)
    }

    override fun onBindViewHolder(holder: PageVH, position: Int) {
        val page = pages[position]
        holder.ivIllustration.setImageResource(page.imageRes)
        holder.tvTitle.text = page.title
        holder.tvDescription.text = page.description
    }

    override fun getItemCount() = pages.size
}
