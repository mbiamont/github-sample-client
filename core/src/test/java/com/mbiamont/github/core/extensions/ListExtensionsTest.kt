package com.mbiamont.github.core.extensions

import org.junit.Test

import org.junit.Assert.*
import java.util.*

class ListExtensionsTest {

    @Test
    fun countItemPerWeek() {
        val data = listOf(validData1, validData2, validData2Bis, validData3, tooOldData, futureData)

        val expected = Array(52) { 0 }
        expected[51] = 1
        expected[49] = 2
        expected[48] = 1

        val actual = data.countItemPerWeekSinceOneYear { it.createdAt }

        assertArrayEquals(expected, actual)
    }

    private companion object {
        val today: Calendar = Calendar.getInstance()
        val twoWeeksAgo: Calendar = Calendar.getInstance().apply {
            add(Calendar.WEEK_OF_YEAR, -2)
        }
        val threeWeeksAgo: Calendar = Calendar.getInstance().apply {
            add(Calendar.WEEK_OF_YEAR, -3)
        }
        val twoYearAgo: Calendar = Calendar.getInstance().apply {
            add(Calendar.YEAR, -2)
        }
        val nextWeek: Calendar = Calendar.getInstance().apply {
            add(Calendar.WEEK_OF_YEAR, 1)
        }

        val validData1 = SampleData("data1", today)
        val validData2 = SampleData("data2", twoWeeksAgo)
        val validData2Bis = SampleData("data2Bis", twoWeeksAgo)

        val validData3 = SampleData("data3", threeWeeksAgo)

        val tooOldData = SampleData("data4", twoYearAgo)
        val futureData = SampleData("data5", nextWeek)
    }
}

data class SampleData(
    val id: String,
    val createdAt: Calendar
)