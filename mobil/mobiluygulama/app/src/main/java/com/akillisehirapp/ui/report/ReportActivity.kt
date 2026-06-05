package com.akillisehirapp.ui.report

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.akillisehirapp.R
import com.akillisehirapp.databinding.ActivityReportBinding
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import java.io.File


class ReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportBinding
    private val viewModel: ReportViewModel by viewModels()


    // ── İzin ve dosya seçici launcher'ları ──────────────────────────────────
    // ReportActivity.kt'de BU KALSIN (orijinal)
    private val konumIzniLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { verildi ->
        if (verildi) gpsKonumAl()
        else Toast.makeText(this, "Konum izni gerekli", Toast.LENGTH_SHORT).show()
    }

    private val kameraIzniLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { verildi ->
        if (verildi) kameraAc()
        else Toast.makeText(this, "Kamera izni olmadan fotoğraf çekilemez", Toast.LENGTH_SHORT).show()
    }

        private val galeriLauncher = registerForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uriList -> uriList.take(3).forEach { viewModel.fotografEkle(it) } }

    private val kameraLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { basarili ->
        if (basarili) {
            viewModel.geciciFotoUri?.let { viewModel.fotografEkle(it) }
        }
    }

    private val bildirimIzniLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { /* sonuç önemsiz */ }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        observeViewModel()
        setupButtons()
        gosterAdim(0)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun observeViewModel() {
        viewModel.adim.observe(this) { adim -> gosterAdim(adim) }

        viewModel.fotograflar.observe(this) { liste ->
            binding.tvFotoSayisi.text = "${liste.size}/3 fotoğraf"
            binding.btnFotoEkle.isEnabled = liste.size < 3
        }

        viewModel.konum.observe(this) { latLng ->
            if (latLng != null) {
                binding.tvKonumBilgisi.text = "%.5f, %.5f".format(latLng.latitude, latLng.longitude)
                binding.btnIleri.isEnabled = true
            }
        }

        viewModel.kategori.observe(this) { kat ->
            binding.btnIleri.isEnabled = (kat != null)
        }

        viewModel.yukleniyor.observe(this) { yukleniyor ->
            binding.btnGonder.isEnabled = !yukleniyor
            binding.btnGonder.text = if (yukleniyor) "Gönderiliyor…" else "Gönder"
            binding.progressGonder.visibility = if (yukleniyor) View.VISIBLE else View.GONE
        }

        viewModel.gonderiSonucu.observe(this) { sonuc ->
            when (sonuc) {
                is GonderiSonucu.Basarili -> gosterBasari(sonuc.takipNo)
                is GonderiSonucu.Hata     -> Toast.makeText(this, sonuc.mesaj, Toast.LENGTH_LONG).show()
                null -> {}
            }
        }
    }

    private fun setupButtons() {
        binding.btnIleri.setOnClickListener {
            if (viewModel.adim.value == 3) viewModel.gonderi()
            else viewModel.ileriGit()
        }
        binding.btnGeri.setOnClickListener { viewModel.geriGit() }

        // Kategori butonları
        binding.btnKaza.setOnClickListener     { viewModel.kategoriSec(OlayKategori.KAZA);      hiliteKategori(0) }
        binding.btnYangin.setOnClickListener   { viewModel.kategoriSec(OlayKategori.YANGIN);    hiliteKategori(1) }
        binding.btnSuBaskini.setOnClickListener{ viewModel.kategoriSec(OlayKategori.SU_BASKINI);hiliteKategori(2) }
        binding.btnAltyapi.setOnClickListener  { viewModel.kategoriSec(OlayKategori.ALTYAPI);   hiliteKategori(3) }
        binding.btnYolHasari.setOnClickListener{ viewModel.kategoriSec(OlayKategori.YOL_HASARI);hiliteKategori(4) }
        binding.btnDiger.setOnClickListener    { viewModel.kategoriSec(OlayKategori.DIGER);     hiliteKategori(5) }

        // Konum
        binding.btnGpsKullan.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) gpsKonumAl()
            else konumIzniLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        binding.btnHaritadanSec.setOnClickListener {
            MapPickerDialog().also { dialog ->
                dialog.onLocationPicked = { latLng ->
                    viewModel.konumGuncelle(latLng)
                }
                dialog.show(supportFragmentManager, "map_picker")
            }
        }

        // Fotoğraf
        binding.btnFotoEkle.setOnClickListener { kaynakSec() }

        // Anonim
        binding.cbAnonim.setOnCheckedChangeListener { _, checked -> viewModel.anonim = checked }

        // Açıklama
        binding.etAciklama.addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) { viewModel.aciklamaGuncelle(s?.toString() ?: "") }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnAnaSayfa.setOnClickListener { finish() }
    }

    private fun gosterAdim(adim: Int) {
        binding.stepKategori.visibility = if (adim == 0) View.VISIBLE else View.GONE
        binding.stepKonum.visibility    = if (adim == 1) View.VISIBLE else View.GONE
        binding.stepFoto.visibility     = if (adim == 2) View.VISIBLE else View.GONE
        binding.stepOzet.visibility     = if (adim == 3) View.VISIBLE else View.GONE
        binding.layoutBasari.visibility = View.GONE

        binding.progressAdim.progress = (adim + 1) * 25
        binding.tvAdimBaslik.text = listOf(
            "1. Kategori Seçin",
            "2. Konumu Belirleyin",
            "3. Fotoğraf Ekleyin",
            "4. Özet & Gönderin"
        ).getOrElse(adim) { "" }

        binding.btnGeri.visibility = if (adim > 0) View.VISIBLE else View.INVISIBLE
        binding.btnIleri.text = if (adim == 3) "Gönder" else "İleri"
        binding.btnIleri.isEnabled = when (adim) {
            0 -> viewModel.kategori.value != null
            1 -> viewModel.konum.value != null
            else -> true
        }
        binding.btnGonder.visibility = View.GONE
    }

    private fun hiliteKategori(secili: Int) {
        val butonlar = listOf(
            binding.btnKaza, binding.btnYangin, binding.btnSuBaskini,
            binding.btnAltyapi, binding.btnYolHasari, binding.btnDiger
        )
        butonlar.forEachIndexed { i, btn ->
            btn.alpha = if (i == secili) 1f else 0.5f
        }
        binding.btnIleri.isEnabled = true
    }

    private fun gpsKonumAl() {
        val client = LocationServices.getFusedLocationProviderClient(this)
        try {
            client.lastLocation.addOnSuccessListener { loc ->
                if (loc != null) {
                    viewModel.konumGuncelle(LatLng(loc.latitude, loc.longitude))
                } else {
                    Toast.makeText(this, "Konum alınamadı, tekrar deneyin", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: SecurityException) {
            konumIzniLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun kaynakSec() {
        android.app.AlertDialog.Builder(this)
            .setTitle("Fotoğraf Ekle")
            .setItems(arrayOf("Kamera", "Galeri")) { _, secim ->
                when (secim) {
                    0 -> {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                            == PackageManager.PERMISSION_GRANTED) {
                            kameraAc()
                        } else {
                            kameraIzniLauncher.launch(Manifest.permission.CAMERA)
                        }
                    }
                    1 -> galeriLauncher.launch("image/*")
                }
            }.show()
    }

    private fun kameraAc() {
        try {
            val fotoDir = externalCacheDir ?: cacheDir
            val dosya = File.createTempFile("foto_", ".jpg", fotoDir)
            val uri = FileProvider.getUriForFile(this, "${packageName}.provider", dosya)
            viewModel.geciciFotoUri = uri
            kameraLauncher.launch(uri)
        } catch (e: Exception) {
            Toast.makeText(this, "Kamera açılamadı: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun gosterBasari(takipNo: String) {
        binding.stepOzet.visibility  = View.GONE
        binding.layoutBasari.visibility = View.VISIBLE
        binding.tvTakipNo.text = "Takip No: $takipNo"
        binding.btnIleri.visibility = View.GONE
        binding.btnGeri.visibility  = View.GONE
    }
}
