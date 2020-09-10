package com.konovalovk.advancedandroidudacity.lesson7.core

import com.konovalovk.advancedandroidudacity.lesson7.R

data class Step(
    val number: String,
    val name: String,
    val caption: String,
    val fragmentId: Int,
    val highlight: Boolean = false)

val data = listOf(
    Step("Step 1",
        "Animations with Motion Layout",
        "Learn how to build a basic animation with Motion Layout. This will crash until you complete the step in the codelab.",
        R.id.step1Fragment
    ),
    Step("Step 2",
        "Animating based on drag events",
        "Learn how to control animations with drag events. This will not display any animation until you complete the step in the codelab.",
        R.id.step2Fragment
    ),
    Step("Step 3",
        "Modifying a path",
        "Learn how to use KeyFrames to modify a path between start and end.",
        R.id.step3Fragment
    ),
    Step("Step 4",
        "Building complex paths",
        "Learn how to use KeyFrames to build complex paths through multiple KeyFrames.",
        R.id.step4Fragment
    ),
    Step("Step 5",
        "Changing attributes with motion",
        "Learn how to resize and rotate views during animations.",
        R.id.step5Fragment
    ),
    Step("Step 6",
        "Changing custom attributes",
        "Learn how to change custom attributes during motion.",
        R.id.step6Fragment
    ),
    Step("Step 7",
        "OnSwipe with complex paths",
        "Learn how to control motion through complex paths with OnSwipe.",
        R.id.step7Fragment
    ),
    Step("Step 8",
        "Running motion with code",
        "Learn how to use MotionLayout to build complex collapsing toolbar animations.",
        R.id.step8Fragment
    )
)