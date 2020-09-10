package com.konovalovk.advancedandroidudacity.lesson5.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.konovalovk.advancedandroidudacity.lesson5.ClippedView
import com.konovalovk.advancedandroidudacity.lesson5.MainViewModel

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ClippedView(requireContext())
    }
}
