package com.konovalovk.advancedandroidudacity.lesson6.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
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

    //Todo: 1.1 Rotate view with provided duration and UI block while it animating
    private fun rotater() {
        //View.ROTATION
        //Todo: 1.3 Refactor rotater
        val animator = ObjectAnimator.ofFloat(star, View.ROTATION, -360f, 0f)
        animator.apply {
            duration = 1000
            disableViewDuringAnimation(rotateButton, this)
        }.start()

    }

    //Todo: 1.2 Implement translater
    private fun translater() {
        //View.TRANSLATION_X
        val animator = ObjectAnimator.ofFloat(star, View.TRANSLATION_X, 200f)
        //Todo: 1.5 Refactor translater
        animator.apply {
            repeatCount = 1 // how many times to repeat (or just tell it to run infinitely).
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(translateButton, this)
        }.start()
    }

    private fun scaler() {
    }

    private fun fader() {
    }

    private fun colorizer() {
    }

    private fun shower() {
    }

    //Todo: 1.4 Add Extension func to block UI during Animation
    private fun ObjectAnimator.disableViewDuringAnimation(view: View, animator: ObjectAnimator) {
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true
            }
        })
    }
}
