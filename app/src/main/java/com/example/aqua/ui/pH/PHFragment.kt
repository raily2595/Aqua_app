package com.example.aqua.ui.pH

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.aqua.R
import com.example.aqua.SharedViewModel

class PHFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel =
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_ph, container, false)
        val textView: TextView = root.findViewById(R.id.phviser)
        sharedViewModel.pHstring.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val picker1: NumberPicker = root.findViewById(R.id.phpicker1)
        val picker2: NumberPicker = root.findViewById(R.id.phpicker2)
        val picker3: NumberPicker = root.findViewById(R.id.phpicker3)
        val picker4: NumberPicker = root.findViewById(R.id.phpicker4)
        picker1.maxValue = 8
        picker1.minValue = 5
        picker1.value = 7
        picker2.maxValue = 9
        picker2.minValue = 0
        picker2.value = 0
        picker3.maxValue = picker1.maxValue
        picker3.minValue = picker1.minValue
        picker3.value = picker1.value
        picker4.maxValue = picker2.maxValue
        picker4.minValue = picker2.minValue
        picker4.value = picker2.value
        return root
    }
}