package com.example.aqua

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _temperaturstring = MutableLiveData<String>().apply {
        value = """${currenttemperatur}ºC"""
    }
    val temperaturstring: LiveData<String> = _temperaturstring

    private val _waterlevelstring = MutableLiveData<String>().apply {
        value = "-3 cm"
    }
    val waterlevelstring: LiveData<String> = _waterlevelstring

    private val _pHstring = MutableLiveData<String>().apply {
        value = "5.0"
    }
    val pHstring: LiveData<String> = _pHstring

    private val _algea = MutableLiveData<Float>().apply {
        value = 0.0f
    }
    val algea: LiveData<Float> = _algea

    private val _quality = MutableLiveData<Int>().apply {
        value = 3
    }

    val quality: LiveData<Int> = _quality

    private val _currenttemperatur = MutableLiveData<Float>().apply {
        value = 25.0f
    }

    val currenttemperatur: LiveData<Float> = _currenttemperatur

}
