package com.mbiamont.github.navigation

import android.app.Activity

data class Route(
    val destinationActivity: Class<out Activity>,
    val finishActivity: Boolean = false,
    val clearActivityStack: Boolean = false
)