package com.konovalovk.advancedandroidudacity.lesson2.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.konovalovk.advancedandroidudacity.lesson2.MainViewModel
import com.konovalovk.advancedandroidudacity.lesson2.R

class MainFragment : Fragment(R.layout.main_fragment) {
    private val viewModel: MainViewModel by viewModels()
}
