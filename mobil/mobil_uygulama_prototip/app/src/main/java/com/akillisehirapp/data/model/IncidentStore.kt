package com.akillisehirapp.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

data class LocalIncident(
    val id: String = UUID.randomUUID().toString(),
    val kategori: String,
    val aciklama: String,
    val lat: Double,
    val lng: Double,
    val takipNo: String,
    val photoUris: List<String> = emptyList(),
    val zaman: String = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
)

object IncidentStore {
    private val _incidents = MutableLiveData<List<LocalIncident>>(emptyList())
    val incidents: LiveData<List<LocalIncident>> = _incidents

    fun ekle(incident: LocalIncident) {
        val liste = _incidents.value?.toMutableList() ?: mutableListOf()
        liste.add(0, incident)
        _incidents.postValue(liste)
    }
}