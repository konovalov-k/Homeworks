package com.konovalovk.advancedandroidudacity.lesson6.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.konovalovk.advancedandroidudacity.lesson6.MainViewModel
import com.konovalovk.advancedandroidudacity.lesson6.R

class MainFragment : Fragment(R.layout.main_fragment) {
    private val viewModel: MainViewModel by viewModels()
}
