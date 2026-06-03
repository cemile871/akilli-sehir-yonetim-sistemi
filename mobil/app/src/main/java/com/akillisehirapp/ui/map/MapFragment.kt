package com.akillisehirapp.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.akillisehirapp.R
import com.akillisehirapp.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private var googleMap: GoogleMap? = null

    // Elazığ koordinatları
    private val ELAZIZ_CENTER = LatLng(38.6748, 39.2225)

    // Demo kavşak konumları
    private val kavsaklar = listOf(
        Pair("Çarşı Kavşağı",     LatLng(38.6760, 39.2235)),
        Pair("Tofaş Kavşağı",     LatLng(38.6780, 39.2210)),
        Pair("İzzet Paşa Kavşağı",LatLng(38.6790, 39.2270)),
        Pair("Palu Yolu Kavşağı", LatLng(38.6720, 39.2180)),
        Pair("Üniversite Kavşağı",LatLng(38.6830, 39.2310))
    )

    private val locationPermLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) enableMyLocation()
        else Toast.makeText(requireContext(), "Konum izni verilmedi", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMapBinding.bind(view)

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.fabMyLocation.setOnClickListener { centerOnMyLocation() }

        binding.chipTraffic.setOnCheckedChangeListener { _, checked ->
            googleMap?.isTrafficEnabled = checked
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Elazığ merkezine odaklan
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ELAZIZ_CENTER, 13f))
        map.isTrafficEnabled = true

        // UI ayarları
        map.uiSettings.apply {
            isZoomControlsEnabled = false
            isCompassEnabled = true
            isMyLocationButtonEnabled = false
        }

        // Kavşak marker'ları
        kavsaklar.forEach { (name, latLng) ->
            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(name)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )
        }

        // Demo olay marker'ı
        map.addMarker(
            MarkerOptions()
                .position(LatLng(38.6755, 39.2240))
                .title("Trafik Kazası")
                .snippet("14 dk önce bildirildi")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        )

        // Marker tıklama
        map.setOnMarkerClickListener { marker ->
            marker.showInfoWindow()
            true
        }

        // Konum izni kontrolü
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            enableMyLocation()
        }
    }

    private fun enableMyLocation() {
        try {
            googleMap?.isMyLocationEnabled = true
        } catch (e: SecurityException) {
            locationPermLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun centerOnMyLocation() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationPermLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            return
        }
        // Gerçek konuma yakınlaştır (şimdilik Elazığ merkezi demo)
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(ELAZIZ_CENTER, 15f))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
