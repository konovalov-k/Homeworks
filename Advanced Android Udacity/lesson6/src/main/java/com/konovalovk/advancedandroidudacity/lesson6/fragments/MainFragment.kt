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
        viewModel.animatorHelper.run {
            rotateButton.setOnClickListener {
                rotater(star, rotateButton)
            }

            translateButton.setOnClickListener {
                translater(star, translateButton)
            }

            scaleButton.setOnClickListener {
                scaler(star, scaleButton)
            }

            fadeButton.setOnClickListener {
                fader(star, fadeButton)
            }

            colorizeButton.setOnClickListener {
                colorizer(star, colorizeButton)
            }

            showerButton.setOnClickListener {
                shower(R.drawable.ic_star, star)
            }
        }
    }
}
