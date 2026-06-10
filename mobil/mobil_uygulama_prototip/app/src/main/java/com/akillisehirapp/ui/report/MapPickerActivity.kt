package com.akillisehirapp.ui.report

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.akillisehirapp.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapPickerActivity : AppCompatActivity(), OnMapReadyCallback {

    private var googleMap: GoogleMap? = null
    private var seciliKonum: LatLng? = null
    private lateinit var tvKonum: TextView
    private lateinit var btnSec: Button

    private val ELAZIZ = LatLng(38.6748, 39.2225)

    private val konumIzniLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { verildi ->
        if (verildi) mevcutKonumaGit()
        else Toast.makeText(this, "Konum izni verilmedi", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_picker)

        tvKonum = findViewById(R.id.tvSeciliKonum)
        btnSec  = findViewById(R.id.btnKonumuSec)
        btnSec.isEnabled = false

        btnSec.setOnClickListener {
            seciliKonum?.let { konum ->
                setResult(RESULT_OK, Intent().apply {
                    putExtra("lat", konum.latitude)
                    putExtra("lng", konum.longitude)
                })
                finish()
            }
        }

        findViewById<Button>(R.id.btnIptal).setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        findViewById<Button>(R.id.btnGpsMapPicker).setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                mevcutKonumaGit()
            } else {
                konumIzniLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }

        // Fragment XML'de android:name ile tanımlandığı için setContentView'da
        // otomatik oluşturulur — beginTransaction/commit gerekmez.
        val mapFrag = supportFragmentManager
            .findFragmentById(R.id.mapPickerContainer) as SupportMapFragment
        mapFrag.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        // Maps yüklenemezse (BlueStacks gibi ortamlarda nadiren olabilir)
        // uncaught exception ile crash'i önlemek için tüm setup try-catch içinde.
        try {
            googleMap = map
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(ELAZIZ, 13f))
            map.uiSettings.isZoomControlsEnabled = true

            map.setOnMapClickListener { latLng ->
                map.clear()
                map.addMarker(MarkerOptions().position(latLng).title("Seçilen Konum"))
                seciliKonum = latLng
                tvKonum.text = "Enlem: %.5f\nBoylam: %.5f"
                    .format(latLng.latitude, latLng.longitude)
                btnSec.isEnabled = true
            }

            // İzin varsa "mavi nokta" göster ve mevcut konuma git
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                try { map.isMyLocationEnabled = true } catch (_: Exception) {}
                mevcutKonumaGit()
            }

        } catch (e: Exception) {
            // Harita kurulumu başarısız — kullanıcı GPS butonunu veya İptal'i kullanabilir
            Toast.makeText(this, "Harita yüklenemedi, GPS'i kullanabilirsiniz", Toast.LENGTH_LONG).show()
        }
    }

    private fun mevcutKonumaGit() {
        try {
            LocationServices.getFusedLocationProviderClient(this)
                .lastLocation
                .addOnSuccessListener { loc ->
                    if (loc != null) {
                        googleMap?.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                LatLng(loc.latitude, loc.longitude), 15f
                            )
                        )
                    } else {
                        Toast.makeText(this, "GPS sinyali bekleniyor…", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (_: Exception) {}
    }
}
