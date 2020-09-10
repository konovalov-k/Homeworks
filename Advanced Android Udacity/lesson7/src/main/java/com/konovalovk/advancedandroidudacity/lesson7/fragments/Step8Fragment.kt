/*
 *   Copyright (C) 2019 The Android Open Source Project
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.konovalovk.advancedandroidudacity.lesson7.fragments

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.konovalovk.advancedandroidudacity.lesson7.R

class Step8Fragment : Fragment(R.layout.fragment_step8) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coordinateMotion(view)
    }

    private fun coordinateMotion(view: View) {
        // TODO: set progress of MotionLayout based on an AppBarLayout.OnOffsetChangedListener
        val appBarLayout: AppBarLayout = view.findViewById(R.id.appbar_layout)
        val motionLayout: MotionLayout = view.findViewById(R.id.motion_layout)

        val listener = AppBarLayout.OnOffsetChangedListener { unused, verticalOffset ->
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()
            motionLayout.progress = seekPosition
        }

        appBarLayout.addOnOffsetChangedListener(listener)
    }
}
