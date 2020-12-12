package com.example.aqua

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val tempbool = MutableLiveData<Boolean>().apply {
        value = false
    }

    private val phbool = MutableLiveData<Boolean>().apply {
        value = false
    }

    private val _temperaturstring = MutableLiveData<String>().apply {
        value = "ºC"
    }
    val temperaturstring: LiveData<String> = _temperaturstring

    private val _waterlevelstring = MutableLiveData<String>().apply {
        value = "-3 cm"
    }
    val waterlevelstring: LiveData<String> = _waterlevelstring

    private val _currentph = MutableLiveData<Float>().apply {
        value = 7.0f
    }
    val currentph: LiveData<Float> = _currentph

    private val _algea = MutableLiveData<Float>().apply {
        value = 0.0f
    }
    val algea: LiveData<Float> = _algea

    val _quality = MutableLiveData<Int>().apply {
        value = 1
    }

    val quality: LiveData<Int> = _quality

    private val _currenttemperatur = MutableLiveData<Int>().apply {
        value = 25
    }

    val currenttemperatur: LiveData<Int> = _currenttemperatur

    val mintemp = MutableLiveData<Int>()
    val maxtemp = MutableLiveData<Int>()
    fun sendMinMax(minm: Int, maxm: Int ) {
        mintemp.value = minm
        maxtemp.value = maxm
        changeQualityTemp()
    }
    fun changeQualityTemp() {
        if ((currenttemperatur.value!! < mintemp.value!!)||(currenttemperatur.value!! > maxtemp.value!!)){
            if (tempbool.value == false){
                tempbool.value = true
                _quality.value = quality.value?.plus(1)
            }
        } else {
            if (tempbool.value == true){
                tempbool.value = false
                _quality.value = quality.value?.minus(1)
            }
        }
        Log.d("qualitet",_quality.value.toString())
    }

    val minph = MutableLiveData<Float>()
    val maxph = MutableLiveData<Float>()
    fun sendMinMaxpH(minph1 : Int, minph2: Int, maxph1 : Int, maxph2 : Int){
        minph.value = minph1.toFloat() + (minph2.toFloat()/10)
        maxph.value = maxph1.toFloat() + (maxph2.toFloat()/10)
        changeQualityPh()
    }

    fun changeQualityPh() {
        if ((currentph.value!! < minph.value!!)||(currentph.value!! > maxph.value!!)){
            if (phbool.value == false){
                phbool.value = true
                _quality.value = quality.value?.plus(1)
            }
        } else {
            if (phbool.value == true){
                _quality.value = quality.value?.minus(1)
                phbool.value = false
            }
        }
    }






}
