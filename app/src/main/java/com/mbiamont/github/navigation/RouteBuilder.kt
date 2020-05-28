package com.mbiamont.github.navigation

import android.app.Activity

class RouteBuilder(private val destinationActivity: Class<out Activity>) {
    var finishActivity: Boolean = false
    var clearActivityStack: Boolean = false

    fun build() = Route(destinationActivity, finishActivity, clearActivityStack)
}

inline fun <reified T : Activity> route(block: RouteBuilder.() -> Unit) = RouteBuilder(T::class.java).apply(block).build()