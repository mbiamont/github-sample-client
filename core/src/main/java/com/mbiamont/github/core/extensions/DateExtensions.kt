package com.mbiamont.github.core.extensions

import java.util.*

fun Date.minusYears(years: Int) = Calendar.getInstance().let {
    time = this.time
    it.add(Calendar.YEAR, -1 * years)
    it.time
}