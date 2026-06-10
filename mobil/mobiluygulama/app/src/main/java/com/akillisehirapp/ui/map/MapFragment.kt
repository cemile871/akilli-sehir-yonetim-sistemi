package com.akillisehirapp.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.akillisehirapp.R
import com.akillisehirapp.databinding.FragmentMapBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.akillisehirapp.data.model.IncidentStore

class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private var googleMap: GoogleMap? = null
    private var fusedClient: FusedLocationProviderClient? = null
    private var locationCallback: LocationCallback? = null
    private var ilkKonumAlindi = false

    private val ELAZIZ_CENTER = LatLng(38.6748, 39.2225)

    private val kavsaklar = listOf(
        Pair("Çarşı Kavşağı",      LatLng(38.6760, 39.2235)),
        Pair("Tofaş Kavşağı",      LatLng(38.6780, 39.2210)),
        Pair("İzzet Paşa Kavşağı", LatLng(38.6790, 39.2270)),
        Pair("Palu Yolu Kavşağı",  LatLng(38.6720, 39.2180)),
        Pair("Üniversite Kavşağı", LatLng(38.6830, 39.2310))
    )

    private val locationPermLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { perms ->
        val verildi = perms[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                      perms[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (verildi) konumTakipBaslat()
        else Toast.makeText(requireContext(), "Konum izni verilmedi", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMapBinding.bind(view)

        fusedClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.fabMyLocation.setOnClickListener { merkezimeBak() }
        binding.chipTraffic.setOnCheckedChangeListener { _, checked ->
            googleMap?.isTrafficEnabled = checked
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Konum izni varsa GPS'e git; yoksa 2 sn sonra Elazığ'a bak (fallback)
        konumIzniKontrol()   // ← tek çağrı, düzeltildi (eskiden sonda tekrar çağrılıyordu)
        Handler(Looper.getMainLooper()).postDelayed({
            if (!ilkKonumAlindi) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(ELAZIZ_CENTER, 13f))
            }
        }, 2000)

        map.isTrafficEnabled = true
        map.uiSettings.isZoomControlsEnabled = false
        map.uiSettings.isCompassEnabled = true
        map.uiSettings.isMyLocationButtonEnabled = false

        // Bildirilen olayları haritada göster (uiSettings.apply dışında — burası doğru yer)
        IncidentStore.incidents.observe(viewLifecycleOwner) { liste ->
            liste.forEach { incident ->
                map.addMarker(
                    MarkerOptions()
                        .position(LatLng(incident.lat, incident.lng))
                        .title(incident.kategori)
                        .snippet("${incident.takipNo} · ${incident.zaman}")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                )
            }
        }

        kavsaklar.forEach { (name, pos) ->
            map.addMarker(MarkerOptions().position(pos).title(name)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        }

        map.addMarker(MarkerOptions()
            .position(LatLng(38.6755, 39.2240))
            .title("Trafik Kazası").snippet("14 dk önce bildirildi")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)))
    }

    private fun konumIzniKontrol() {
        val fine   = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
        val coarse = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
        if (fine == PackageManager.PERMISSION_GRANTED || coarse == PackageManager.PERMISSION_GRANTED) {
            konumTakipBaslat()
        } else {
            locationPermLauncher.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ))
        }
    }

    private fun konumTakipBaslat() {
        try {
            googleMap?.isMyLocationEnabled = true
        } catch (_: SecurityException) {}

        val istek = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000L)
            .setMinUpdateIntervalMillis(2000L)
            .build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val konum = result.lastLocation ?: return
                val latLng = LatLng(konum.latitude, konum.longitude)
                if (!ilkKonumAlindi) {
                    googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                    ilkKonumAlindi = true
                }
            }
        }

        try {
            fusedClient?.requestLocationUpdates(istek, locationCallback!!, Looper.getMainLooper())
        } catch (_: SecurityException) {}
    }

    private fun merkezimeBak() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            konumIzniKontrol(); return
        }
        try {
            fusedClient?.lastLocation?.addOnSuccessListener { loc ->
                if (loc != null) {
                    googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        LatLng(loc.latitude, loc.longitude), 15f))
                }
            }
        } catch (_: SecurityException) {}
    }

    override fun onResume() {
        super.onResume()
        locationCallback?.let { cb ->
            val istek = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000L).build()
            try { fusedClient?.requestLocationUpdates(istek, cb, Looper.getMainLooper()) }
            catch (_: SecurityException) {}
        }
    }

    override fun onPause() {
        super.onPause()
        locationCallback?.let { fusedClient?.removeLocationUpdates(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        locationCallback?.let { fusedClient?.removeLocationUpdates(it) }
        _binding = null
    }
}
