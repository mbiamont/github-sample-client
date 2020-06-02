package com.mbiamont.github.core.extensions

import java.util.*

inline fun <T> List<T>.countItemPerWeekSinceOneYear(block: (T) -> Calendar) =
    countItemPerWeek(Calendar.getInstance().apply { add(Calendar.YEAR, -1) }, block)

/**
 * Count each element of the list for each week since the specified date.
 * It returns an array containing the count for each week. The size of this array is always 52.
 *
 * The parameter block allows to return for each element the associated calendar.
 */
inline fun <T> List<T>.countItemPerWeek(since: Calendar, block: (T) -> Calendar): Array<Int> {
    val array = Array(52) { 0 }

    forEach {
        val cal = block(it)

        //We calculate the number of week between $since and the object time
        val deltaWeek = ((cal.timeInMillis - since.timeInMillis) / MILLIS_IN_WEEK).toInt()

        if (deltaWeek in 1..52) {
            array[deltaWeek - 1]++
        }
    }

    return array
}

const val MILLIS_IN_WEEK = 1000 * 60 * 60 * 24 * 7