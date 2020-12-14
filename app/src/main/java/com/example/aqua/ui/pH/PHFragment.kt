package com.example.aqua.ui.pH

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
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


class PHFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_ph, container, false)
        val textView: TextView = root.findViewById(R.id.phviser)
        sharedViewModel.currentph.observe(viewLifecycleOwner, Observer {
            textView.text = it.toString()
        })

        val graph: LineChart = root.findViewById(R.id.graph)
        val values: ArrayList<Entry> = ArrayList()
        values.add(Entry(0.0f, 5.7f))
        values.add(Entry(1.0f, 5.8f))
        values.add(Entry(2.0f, 5.9f))
        values.add(Entry(3.0f, 6.0f))
        values.add(Entry(4.0f, 5.9f))
        values.add(Entry(5.0f, 6.0f))
        values.add(Entry(6.0f, 6.1f))
        values.add(Entry(7.0f, 6.2f))
        values.add(Entry(8.0f, 6.3f))
        values.add(Entry(9.0f, 6.2f))
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
        yAxis.axisMaximum = 14f;
        yAxis.axisMinimum = 0f;
        var i = 10f
        sharedViewModel.currentph.observe(viewLifecycleOwner, Observer {
            graph.data.getDataSetByIndex(0).addEntry(Entry(i, it))
            graph.xAxis.axisMinimum = (i-10)
            graph.xAxis.axisMaximum = i
            i++
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

        picker1.setOnValueChangedListener(NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            picker3.minValue = newVal
            sharedViewModel.sendMinMaxpH(picker1.value, picker2.value, picker3.value, picker4.value)
        })

        picker2.setOnValueChangedListener(NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            picker4.minValue = newVal
            sharedViewModel.sendMinMaxpH(picker1.value, picker2.value, picker3.value, picker4.value)
        })

        picker3.setOnValueChangedListener(NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            sharedViewModel.sendMinMaxpH(picker1.value, picker2.value, picker3.value, picker4.value)
        })

        picker4.setOnValueChangedListener(NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            sharedViewModel.sendMinMaxpH(picker1.value, picker2.value, picker3.value, picker4.value)
        })

        sharedViewModel.minph.observe(viewLifecycleOwner, Observer {
            val pick = it - it.toInt()
            picker2.value = (pick * 10).toInt()
            picker1.value = it.toInt()
        })

        sharedViewModel.maxph.observe(viewLifecycleOwner, Observer {
            val pick = it - it.toInt()
            picker4.value = (pick * 10).toInt()
            picker3.value = it.toInt()
        })



        return root
    }
}