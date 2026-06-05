package com.akillisehirapp.ui.report

import android.Manifest
import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.akillisehirapp.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.annotation.SuppressLint
import androidx.core.app.ActivityCompat

class MapPickerDialog : DialogFragment(), OnMapReadyCallback {

    private var googleMap: GoogleMap? = null
    private var seciliKonum: LatLng? = null
    var onLocationPicked: ((LatLng) -> Unit)? = null

    private val ELAZIZ = LatLng(38.6748, 39.2225)
    private var tvKonumRef: TextView? = null
    private var btnSecRef: Button? = null

    private val konumIzniLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { verildi ->
        if (verildi) mevcutKonumaGit()
        else Toast.makeText(requireContext(), "Konum izni verilmedi", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val d = super.onCreateDialog(savedInstanceState)
        d.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return d
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.dialog_map_picker, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvKonum = view.findViewById<TextView>(R.id.tvSeciliKonum)
        val btnSec  = view.findViewById<Button>(R.id.btnKonumuSec)
        tvKonumRef  = tvKonum
        btnSecRef   = btnSec
        btnSec.isEnabled = false

        btnSec.setOnClickListener {
            seciliKonum?.let { konum ->
                onLocationPicked?.invoke(konum)
                dismiss()
            }
        }

        view.findViewById<Button>(R.id.btnIptal).setOnClickListener { dismiss() }

        view.findViewById<Button>(R.id.btnGpsMapPicker).setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mevcutKonumaGit()
            } else {
                konumIzniLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }

        // childFragmentManager — MapFragment ile birebir aynı yaklaşım
        val mapFrag = (childFragmentManager.findFragmentById(R.id.mapPickerContainer)
                as? SupportMapFragment)
            ?: SupportMapFragment.newInstance().also { frag ->
                childFragmentManager.beginTransaction()
                    .replace(R.id.mapPickerContainer, frag)
                    .commitNow()
            }
        mapFrag.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap) {
        try {
            googleMap = map
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(ELAZIZ, 13f))
            map.uiSettings.isZoomControlsEnabled = true

            map.setOnMapClickListener { latLng ->
                map.clear()
                map.addMarker(MarkerOptions().position(latLng).title("Seçilen Konum"))
                seciliKonum = latLng
                tvKonumRef?.text = "Enlem: %.5f\nBoylam: %.5f"
                    .format(latLng.latitude, latLng.longitude)
                btnSecRef?.isEnabled = true
            }

            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                    map.isMyLocationEnabled = true
                }
            }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Harita yüklenemedi", Toast.LENGTH_LONG).show()
        }
    }

    private fun mevcutKonumaGit() {
        try {
            LocationServices.getFusedLocationProviderClient(requireContext())
                .lastLocation.addOnSuccessListener { loc ->
                    if (loc != null) {
                        googleMap?.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                LatLng(loc.latitude, loc.longitude), 15f
                            )
                        )
                    }
                }
        } catch (_: Exception) {}
    }
}