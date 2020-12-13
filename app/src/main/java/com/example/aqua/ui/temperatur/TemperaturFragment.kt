package com.example.aqua.ui.temperatur

import android.os.Bundle
import android.util.Log
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
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


class TemperaturFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_temperatur, container, false)
        val textView: TextView = root.findViewById(R.id.tempviser)
        sharedViewModel.currenttemperatur.observe(viewLifecycleOwner, Observer {
            val text = it.toString() + "ºC"
            textView.text = text
        })

        val graph: LineChart = root.findViewById(R.id.graph)
        val values: ArrayList<Entry> = ArrayList()
        values.add(Entry(0.0f, 23f))
        values.add(Entry(1.0f, 23f))
        values.add(Entry(2.0f, 24f))
        values.add(Entry(3.0f, 24f))
        values.add(Entry(4.0f, 23f))
        values.add(Entry(5.0f, 22f))
        values.add(Entry(6.0f, 23f))
        values.add(Entry(7.0f, 24f))
        values.add(Entry(8.0f, 22f))
        values.add(Entry(9.0f, 23f))
        val set1 = LineDataSet(values, "DataSet")
        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(set1)
        val data = LineData(dataSets)
        graph.data = data
        graph.animateX(3000);
        graph.description.isEnabled = false;
        graph.legend.isEnabled = false;
        graph.isDragEnabled = true;
        graph.setScaleEnabled(true);
        graph.axisRight.isEnabled = false;
        set1.lineWidth = 3f;
        val yAxis = graph.axisLeft;
        yAxis.axisMaximum = 30f;
        yAxis.axisMinimum = 15f;
        var i = 10f
        sharedViewModel.currentph.observe(viewLifecycleOwner, Observer {
            graph.data.getDataSetByIndex(0).addEntry(Entry(i, it))
            i++
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
        })

        picker2.setOnValueChangedListener(OnValueChangeListener { picker, oldVal, newVal ->
            sharedViewModel.sendMinMax(picker1.value, picker2.value)
        })

        sharedViewModel.mintemp.observe(viewLifecycleOwner, Observer {
            picker1.value = it
            Log.d("kake", "polse")
        })

        sharedViewModel.maxtemp.observe(viewLifecycleOwner, Observer {
            picker2.value = it
            Log.d("kake", "polse")
        })

        return root;
    }
}