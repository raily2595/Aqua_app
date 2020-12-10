package com.example.aqua.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.aqua.R
import com.example.aqua.SharedViewModel

class HomeFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
    //    val fishView: ImageView = root.findViewById(R.id.fish)
        val algeaView: ImageView = root.findViewById(R.id.algea)
        sharedViewModel.algea.observe(viewLifecycleOwner, Observer {
            algeaView.alpha = it
        })

        sharedViewModel.quality.observe(viewLifecycleOwner, Observer {
            when(it) {
                1  -> root.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
                2  -> root.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.yellow))
                3  -> root.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.aqua))
            }
        })

        return root
    }
}