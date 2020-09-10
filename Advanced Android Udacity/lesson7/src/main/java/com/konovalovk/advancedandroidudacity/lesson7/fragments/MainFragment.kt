package com.konovalovk.advancedandroidudacity.lesson7.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.konovalovk.advancedandroidudacity.lesson7.MainViewModel
import com.konovalovk.advancedandroidudacity.lesson7.R
import com.konovalovk.advancedandroidudacity.lesson7.core.MainAdapter
import com.konovalovk.advancedandroidudacity.lesson7.core.data
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = MainAdapter(data, findNavController()::navigate)
    }
}
