package com.konovalovk.advancedandroidudacity.lesson6.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View

class AnimatorHelper {
    //Todo: 1.1 Rotate view with provided duration and UI block while it animating
    fun rotater(animateView: View, blockView: View) {
        //View.ROTATION
        //Todo: 1.3 Refactor rotater
        val animator = ObjectAnimator.ofFloat(animateView, View.ROTATION, -360f, 0f)
        animator.apply {
            duration = 1000
            disableViewDuringAnimation(blockView, this)
        }.start()

    }

    //Todo: 1.2 Implement translater
    fun translater(animateView: View, blockView: View) {
        //View.TRANSLATION_X
        val animator = ObjectAnimator.ofFloat(animateView, View.TRANSLATION_X, 200f)
        //Todo: 1.5 Refactor translater
        animator.apply {
            repeatCount = 1 // how many times to repeat (or just tell it to run infinitely).
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(blockView, this)
        }.start()
    }

    //Todo: 1.6 Implement scaler
    fun scaler(animateView: View, blockView: View) {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f) //hold the property and value information for the animation, not the target.
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(animateView, scaleX, scaleY)
        animator.apply {
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(blockView, this)
        }.start()
    }

    fun fader() {
    }

    fun colorizer() {
    }

    fun shower() {
    }

    //Todo: 1.4 Add Extension func to block UI during Animation
    fun ObjectAnimator.disableViewDuringAnimation(view: View, animator: ObjectAnimator) {
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