package com.akillisehirapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.akillisehirapp.R
import com.akillisehirapp.databinding.ActivityMainBinding
import com.akillisehirapp.ui.report.ReportActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navigation
        val navHost = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHost.navController

        // Bottom Navigation ↔ NavController bağla
        binding.bottomNavigationView.setupWithNavController(navController)

        // FAB → Bildir (Aşama 2'de ReportActivity gelecek)
        binding.fabReport.setOnClickListener {
            startActivity(Intent(this, ReportActivity::class.java))
        }

        // Harita fragmanında FAB'ı gizle (haritanın kendi FAB'ı var)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.fabReport.visibility = when (destination.id) {
                R.id.mapFragment -> View.GONE
                else             -> View.VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
