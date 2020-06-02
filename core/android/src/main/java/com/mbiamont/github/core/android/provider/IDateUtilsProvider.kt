package com.mbiamont.github.core.android.provider

import android.content.Context

interface IDateUtilsProvider {

    fun getRelativeDateTimeString(c: Context, time: Long, minResolution: Long, transitionResolution: Long, flags: Int): String
}