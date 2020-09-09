package com.konovalovk.advancedandroidudacity.lesson3.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.konovalovk.advancedandroidudacity.lesson3.MainViewModel
import com.konovalovk.advancedandroidudacity.lesson3.R

class MainFragment : Fragment(R.layout.main_fragment) {
    private val viewModel: MainViewModel by viewModels()
}
