package com.konovalovk.advancedandroidudacity.lesson6.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.konovalovk.advancedandroidudacity.lesson6.MainViewModel
import com.konovalovk.advancedandroidudacity.lesson6.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        rotateButton.setOnClickListener {
            rotater()
        }

        translateButton.setOnClickListener {
            translater()
        }

        scaleButton.setOnClickListener {
            scaler()
        }

        fadeButton.setOnClickListener {
            fader()
        }

        colorizeButton.setOnClickListener {
            colorizer()
        }

        showerButton.setOnClickListener {
            shower()
        }
    }

    private fun rotater() {
    }

    private fun translater() {
    }

    private fun scaler() {
    }

    private fun fader() {
    }

    private fun colorizer() {
    }

    private fun shower() {
    }
}
