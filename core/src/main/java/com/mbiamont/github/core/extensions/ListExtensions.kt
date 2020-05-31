package com.mbiamont.github.core.extensions

import java.util.*

inline fun <T> List<T>.countItemPerWeekSinceOneYear(block: (T) -> Calendar) =
    countItemPerWeek(Calendar.getInstance().apply { add(Calendar.YEAR, -1) }, block)

inline fun <T> List<T>.countItemPerWeek(since: Calendar, block: (T) -> Calendar): Array<Int> {
    val sinceWeek = since.get(Calendar.WEEK_OF_YEAR)
    val sinceYear = since.get(Calendar.YEAR)

    val array = Array(52) { 0 }

    forEach {
        val cal = block(it)
        val weekOfYear = cal.get(Calendar.WEEK_OF_YEAR)
        val year = cal.get(Calendar.YEAR)

        val diffYear = year - sinceYear
        val diffWeek = weekOfYear - sinceWeek

        val adjustedWeek = diffWeek + (diffYear * 52)

        if (adjustedWeek in 0..51) {
            array[adjustedWeek]++
        }
    }

    return array
}