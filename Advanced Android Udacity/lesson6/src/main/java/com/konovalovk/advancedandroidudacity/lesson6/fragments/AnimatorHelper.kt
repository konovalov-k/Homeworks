package com.konovalovk.advancedandroidudacity.lesson6.fragments

import android.animation.*
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView

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
    /**
     * Repetition is a way of telling animations to do the same task again and again.
     * You can specify how many times to repeat (or just tell it to run infinitely).
     * You can also specify the repetition behavior,
     * either REVERSE (for reversing the direction every time it repeats) or RESTART
     * (for animating from the original start value to the original end value, thus repeating in the same direction every time).
     * */
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
    /**
     * There is no single property that scales in both the x and y dimensions,
     * so animations that scale in both x and y need to animate both of these separate properties in parallel.
     * */
    fun scaler(animateView: View, blockView: View) {
        //View.SCALE_X & View.SCALE_Y
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f) //4x //hold the property and value information for the animation, not the target.
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(animateView, scaleX, scaleY)
        animator.apply {
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(blockView, this)
        }.start()
    }

    //Todo: 1.7 Implement fader
    /**
     * “alpha” is a term generally used, especially in computer graphics, to denote the amount of opacity in an object.
     * A value of 0 indicates that the object is completely transparent,
     * and a value of 1 indicates that the object is completely opaque.
     * View objects have a default value of 1.
     * Animations that fade views in or out animate the alpha value between 0 and 1.*/
    fun fader(animateView: View, blockView: View) {
        //View.ALPHA
        val animator = ObjectAnimator.ofFloat(animateView, View.ALPHA, 0f)
        animator.apply {
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(blockView, this)
        }.start()
    }

    //Todo: 1.8 Implement colorizer
    fun colorizer(animateView: View, blockView: View) {
        //XMl property like backgroundColor
        val animator = ObjectAnimator.ofArgb(animateView.parent, "backgroundColor", Color.BLACK, Color.RED)
        animator.apply {
            duration = 500
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(blockView, this)
        }.start()
    }

    //Todo: 1.9 Implement star shower
    fun shower(animateDrawable: Int, animateView: View) {
        (animateView.parent as ViewGroup).also { container ->
            val newStar = initNewStar(animateView, animateDrawable, container.width)
            container.addView(newStar)
            initAnimationFor(newStar, container).start()
        }
    }

    private fun initNewStar(animateView: View, animateDrawable: Int, containerWidth: Int): AppCompatImageView{
        var starW: Float = animateView.width.toFloat()
        var starH: Float = animateView.height.toFloat()
        val newStar = AppCompatImageView(animateView.context)
        newStar.run {
            setImageResource(animateDrawable)
            layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            scaleX = Math.random().toFloat() * 1.5f + .1f
            scaleY = scaleX
            starW *= scaleX
            starH *= scaleY
            translationX = Math.random().toFloat() * containerWidth - starW / 2
        }
        return newStar
    }

    private fun initAnimationFor(animateView: AppCompatImageView, container: ViewGroup): AnimatorSet {
        val animatedViewHeight = animateView.height.toFloat()
        val containerH = container.height.toFloat()
        val mover = ObjectAnimator.ofFloat(animateView, View.TRANSLATION_Y, -animatedViewHeight, containerH + animatedViewHeight).also {
            it.interpolator = AccelerateInterpolator(1f)
        }
        val rotator = ObjectAnimator.ofFloat(animateView, View.ROTATION, (Math.random() * 1080).toFloat()).also {
            it.interpolator = LinearInterpolator()
        }

        val set = AnimatorSet()
        return set.apply {
            playTogether(mover, rotator)
            duration = (Math.random() * 1500 + 500).toLong()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    container.removeView(animateView) //Important remove a view when the animation to move it off the screen is complete.
                }
            })
        }
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