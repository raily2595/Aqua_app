package com.example.aqua.ui.test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.aqua.R
import com.example.aqua.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class TestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("kake", "polse")
        val sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_test, container, false)
        val button: Button = root.findViewById(R.id.button)
        Log.d("kake1", "polse1")

        var intext: TextInputEditText = root.findViewById(R.id.stringtest)
        button.setOnClickListener {
            sharedViewModel.getSuperString(intext.text.toString())
            Snackbar.make(it, "Values accepted", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        return root
    }
}