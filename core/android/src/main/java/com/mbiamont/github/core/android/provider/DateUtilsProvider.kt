package com.mbiamont.github.core.android.provider

import android.content.Context
import android.text.format.DateUtils

class DateUtilsProvider : IDateUtilsProvider {
    override fun getRelativeDateTimeString(c: Context, time: Long, minResolution: Long, transitionResolution: Long, flags: Int) =
        DateUtils.getRelativeDateTimeString(c, time, minResolution, transitionResolution, flags).toString()
}