package com.mbiamont.github.service.mapper

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class RemoteDateMapperTest {

    private lateinit var mapper:  RemoteDateMapper

    @Before
    fun setUp() {
        mapper = RemoteDateMapper()
    }

    @Test
    fun mapString() {
        val expected = date
        val actual = mapper.map(dateStr)

        assertEquals(expected, actual)
    }

    @Test
    fun mapDate() {
        val expected = dateStr
        val actual = mapper.map(date)

        assertEquals(expected, actual)
    }

    @Test
    fun mapToCalendar() {
        val expected = Calendar.getInstance().apply {
            time = date
        }
        val actual = mapper.mapToCalendar(dateStr)

        assertEquals(expected, actual)
    }

    private companion object {
        val date = Date(1593530253000)
        const val dateStr = "2020-06-30T17:17:33Z"
    }
}