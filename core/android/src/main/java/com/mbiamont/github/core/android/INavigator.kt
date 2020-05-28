package com.mbiamont.github.core.android

interface INavigator {

    fun navigateTo(route: String, extras: Map<String, Any>? = null)

    fun navigateBack()

    fun attachCurrentActivity(activity: BaseActivity)

    fun detachCurrentActivity(activity: BaseActivity)
}