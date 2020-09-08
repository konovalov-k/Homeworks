/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.konovalovk.advancedandroidudacity.lesson2.fragments

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.firebase.messaging.FirebaseMessaging
import com.konovalovk.advancedandroidudacity.lesson2.R
import com.konovalovk.advancedandroidudacity.lesson2.util.setElapsedTime
import kotlinx.android.synthetic.main.fragment_egg_timer.*

class EggTimerFragment : Fragment(R.layout.fragment_egg_timer) {
    val viewModel: EggTimerViewModel by viewModels()

    private val TOPIC = "breakfast"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
        // TODO: Step 1.7 call create channel
        createChannel(getString(R.string.egg_notification_channel_id), getString(R.string.egg_notification_channel_name))
        // TODO: Step 3.1 create a new channel for FCM
        createChannel(getString(R.string.breakfast_notification_channel_id), getString(R.string.breakfast_notification_channel_name))
        // TODO: Step 3.4 call subscribe topics on start
        subscribeTopic()
    }

    private fun initListeners() {
        minutes_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.setTimeSelected(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        on_off_switch.setOnCheckedChangeListener { compoundButton, isChecked ->
            viewModel.setAlarm(isChecked)
        }
    }

    private fun initObservers() {
        viewModel.elapsedTime.observe(viewLifecycleOwner, Observer {
            textView.setElapsedTime(it)
        })
    }

    private fun createChannel(channelId: String, channelName: String) {
        // TODO: Step 1.6 START create a channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                // TODO: Step 2.4 change importance
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                // TODO: Step 2.6 disable badges for this channel
                setShowBadge(false)

                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                description = "Time for breakfast"
            }

            // TODO: Step 1.6 END create a channel
            val notificationManager = requireActivity().getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    // TODO: Step 3.3 subscribe to breakfast topic
    private fun subscribeTopic() {
        // [START subscribe_topics]
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC).addOnCompleteListener { task ->
                var msg = getString(R.string.message_subscribed)
                if (!task.isSuccessful) {
                    msg = getString(R.string.message_subscribe_failed)
                }
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
        // [END subscribe_topics]
    }
}

