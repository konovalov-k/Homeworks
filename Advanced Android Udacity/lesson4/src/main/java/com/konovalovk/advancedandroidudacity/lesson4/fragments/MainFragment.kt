package com.konovalovk.advancedandroidudacity.lesson4.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.konovalovk.advancedandroidudacity.lesson4.MainViewModel
import com.konovalovk.advancedandroidudacity.lesson4.R

class MainFragment : Fragment(R.layout.main_fragment) {
    private val viewModel: MainViewModel by viewModels()
}
