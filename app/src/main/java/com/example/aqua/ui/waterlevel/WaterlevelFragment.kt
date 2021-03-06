package com.example.aqua.ui.waterlevel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aqua.R
import com.example.aqua.SharedViewModel

class WaterlevelFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_waterlevel, container, false)
        val textView: TextView = root.findViewById(R.id.waterlevelviser)
        sharedViewModel.waterlevel.observe(viewLifecycleOwner, Observer {
            val text = it.toString() + "cm"
            textView.text = text
        })
        return root
    }
}