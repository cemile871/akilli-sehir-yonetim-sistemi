package com.akillisehirapp.ui.report

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import com.akillisehirapp.data.model.IncidentStore
import com.akillisehirapp.data.model.LocalIncident
import com.akillisehirapp.data.api.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import kotlinx.coroutines.delay

enum class OlayKategori(val etiket: String, val ikonRes: Int) {
    KAZA("Trafik Kazası", android.R.drawable.ic_dialog_alert),
    YANGIN("Yangın",      android.R.drawable.ic_dialog_alert),
    SU_BASKINI("Su Baskını", android.R.drawable.ic_dialog_alert),
    ALTYAPI("Altyapı Arızası", android.R.drawable.ic_menu_manage),
    YOL_HASARI("Yol Hasarı", android.R.drawable.ic_menu_directions),
    DIGER("Diğer",        android.R.drawable.ic_menu_report_image)
}

sealed class GonderiSonucu {
    data class Basarili(val takipNo: String) : GonderiSonucu()
    data class Hata(val mesaj: String)       : GonderiSonucu()
}

class ReportViewModel(application: Application) : AndroidViewModel(application) {

    private val apiService by lazy { RetrofitClient.create(application) }

    var geciciFotoUri: Uri? = null

    private val _adim = MutableLiveData(0)
    val adim: LiveData<Int> = _adim

    private val _kategori = MutableLiveData<OlayKategori?>()
    val kategori: LiveData<OlayKategori?> = _kategori

    private val _konum = MutableLiveData<LatLng?>()
    val konum: LiveData<LatLng?> = _konum

    private val _fotograflar = MutableLiveData<MutableList<Uri>>(mutableListOf())
    val fotograflar: LiveData<MutableList<Uri>> = _fotograflar

    private val _aciklama = MutableLiveData("")
    val aciklama: LiveData<String> = _aciklama

    var anonim: Boolean = false

    private val _gonderiSonucu = MutableLiveData<GonderiSonucu?>()
    val gonderiSonucu: LiveData<GonderiSonucu?> = _gonderiSonucu

    private val _yukleniyor = MutableLiveData(false)
    val yukleniyor: LiveData<Boolean> = _yukleniyor

    fun kategoriSec(k: OlayKategori) { _kategori.value = k }
    fun konumGuncelle(l: LatLng)     { _konum.value = l }
    fun aciklamaGuncelle(s: String)  { _aciklama.value = s }

    fun fotografEkle(uri: Uri) {
        val liste = _fotograflar.value ?: mutableListOf()
        if (liste.size < 3) { liste.add(uri); _fotograflar.value = liste }
    }

    fun fotografSil(uri: Uri) {
        val liste = _fotograflar.value ?: return
        liste.remove(uri)
        _fotograflar.value = liste
    }

    fun ileriGit()  { if ((_adim.value ?: 0) < 3) _adim.value = (_adim.value ?: 0) + 1 }
    fun geriGit()   { if ((_adim.value ?: 0) > 0) _adim.value = (_adim.value ?: 0) - 1 }

    fun gonderi() {
        viewModelScope.launch {
            _yukleniyor.value = true
            try {
                val konum = _konum.value ?: throw Exception("Konum bilgisi bulunamadı")
                
                // Construct RequestBody parts
                val textType = "text/plain".toMediaTypeOrNull()
                val kategoriPart = (_kategori.value?.etiket ?: "Diğer").toRequestBody(textType)
                val aciklamaPart = (_aciklama.value ?: "").toRequestBody(textType)
                val enlemPart = konum.latitude.toString().toRequestBody(textType)
                val boylamPart = konum.longitude.toString().toRequestBody(textType)
                
                val userPrefs = getApplication<Application>().getSharedPreferences("user_prefs", android.content.Context.MODE_PRIVATE)
                val isGuest = userPrefs.getBoolean("is_guest", false)
                val userName = if (anonim) "Anonim Vatandaş" else (if (isGuest) "Misafir Vatandaş" else "Vatandaş")
                val reportedByPart = userName.toRequestBody(textType)
                
                val response = apiService.bildirimGonder(
                    kategoriPart,
                    aciklamaPart,
                    enlemPart,
                    boylamPart,
                    reportedByPart,
                    emptyList()
                )
                
                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!
                    val takipNo = body.takipNo
                    
                    IncidentStore.ekle(
                        LocalIncident(
                            kategori  = _kategori.value?.etiket ?: "Diğer",
                            aciklama  = _aciklama.value ?: "",
                            lat       = konum.latitude,
                            lng       = konum.longitude,
                            takipNo   = takipNo,
                            photoUris = _fotograflar.value?.map { it.toString() } ?: emptyList()
                        )
                    )
                    _gonderiSonucu.value = GonderiSonucu.Basarili(takipNo)
                } else {
                    _gonderiSonucu.value = GonderiSonucu.Hata("Hata: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                _gonderiSonucu.value = GonderiSonucu.Hata(e.message ?: "Bilinmeyen hata")
            } finally {
                _yukleniyor.value = false
            }
        }
    }
}
