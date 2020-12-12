package com.example.aqua

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

    private val _waterlevel = MutableLiveData<Int>().apply {
        value = -3
    }
    val waterlevel: LiveData<Int> = _waterlevel

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
        changeQuality()
    }
    fun changeQuality() {
        if ((currenttemperatur.value!! < mintemp.value!!)||(currenttemperatur.value!! > maxtemp.value!!)){
            if (tempbool.value == false){
                tempbool.value = true
            }
        } else {
            if (tempbool.value == true){
                tempbool.value = false
            }
        }

        if ((currentph.value!! < minph.value!!)||(currentph.value!! > maxph.value!!)){
            if (phbool.value == false){
                phbool.value = true
            }
        } else {
            if (phbool.value == true){
                phbool.value = false
            }
        }
        if ((phbool.value == false) &&(tempbool.value == false)){
            _quality.value = 1
        } else if ((phbool.value == true) &&(tempbool.value == true)){
            _quality.value = 3
        } else {
            _quality.value = 2
        }
    }

    val minph = MutableLiveData<Float>()
    val maxph = MutableLiveData<Float>()
    fun sendMinMaxpH(minph1 : Int, minph2: Int, maxph1 : Int, maxph2 : Int){
        minph.value = minph1.toFloat() + (minph2.toFloat()/10)
        maxph.value = maxph1.toFloat() + (maxph2.toFloat()/10)
        changeQuality()
    }

    fun setAllValue(temp: Int, qual: Int, ph: Float, water: Int, alg: Float, mintem: Int, maxtem: Int, miph: Float, maph: Float){
        _currenttemperatur.value = temp
        _quality.value = qual
        _currentph.value = ph
        _waterlevel.value = water
        _algea.value = alg
        mintemp.value = mintem
        maxtemp.value = maxtem
        minph.value = miph
        maxph.value = maph
    }
}
