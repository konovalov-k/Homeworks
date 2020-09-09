package com.konovalovk.advancedandroidudacity.lesson4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.konovalovk.advancedandroidudacity.lesson4.MainViewModel
import com.konovalovk.advancedandroidudacity.lesson4.MyCanvasView
import com.konovalovk.advancedandroidudacity.lesson4.R

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    //Todo: 1.2 Set fullscreen
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myCanvasView = MyCanvasView(requireContext())
        myCanvasView.run {
            systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
            contentDescription = getString(R.string.canvasContentDescription)
        }
        return myCanvasView//super.onCreateView(inflater, container, savedInstanceState)
    }
}
