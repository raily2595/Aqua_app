package com.example.aqua.ui.temperatur

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.NumberPicker.OnValueChangeListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aqua.R
import com.example.aqua.SharedViewModel


class TemperaturFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_temperatur, container, false)
        val textView: TextView = root.findViewById(R.id.tempviser)
        sharedViewModel.temperaturstring.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val picker1: NumberPicker = root.findViewById(R.id.temppicker1)
        val picker2: NumberPicker = root.findViewById(R.id.temppicker2)
        picker1.maxValue = 31
        picker1.minValue = 15
        picker1.value = 25
        picker2.maxValue = picker1.maxValue
        picker2.minValue = picker1.minValue
        picker2.value = picker1.value

        picker1.setOnValueChangedListener(OnValueChangeListener { picker, oldVal, newVal ->
            picker2.minValue = newVal
            sharedViewModel.sendMinMax(picker1.value, picker2.value)
        });

        picker2.setOnValueChangedListener(OnValueChangeListener { picker, oldVal, newVal ->
            sharedViewModel.sendMinMax(picker1.value, picker2.value)
        });

        return root;
    }
}