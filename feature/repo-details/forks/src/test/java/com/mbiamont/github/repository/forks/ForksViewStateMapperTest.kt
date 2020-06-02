package com.mbiamont.github.repository.forks

import android.content.Context
import android.text.format.DateUtils
import com.mbiamont.github.core.android.provider.IDateUtilsProvider
import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState
import com.mbiamont.github.domain.entity.Fork
import com.mbiamont.github.domain.entity.User
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class ForksViewStateMapperTest {

    private val context: Context = mock()
    private val dateUtilsProvider: IDateUtilsProvider = mock()

    private lateinit var viewStateMapper: ForksViewStateMapper

    @Before
    fun setUp() {
        viewStateMapper = ForksViewStateMapper(context, dateUtilsProvider)
    }

    @Test
    fun mapProgressViewState() {
        val expected = ProgressViewState(true)

        val actual = viewStateMapper.map(true)

        assertEquals(expected, actual)
    }

    @Test
    fun mapTimeSerieViewState() {
        val dataset = Array(2) { 0 }.apply { this[0] = 1 }

        val expected = TimeSerieViewState(dataset)

        val actual = viewStateMapper.map(dataset)

        assertEquals(expected, actual)
    }

    @Test
    fun mapForkViewState() {
        val expected = ForkViewState(
            ownerAvatarUrl = avatarUrl,
            ownerLogin = ownerLogin,
            dateLabel = date,
            forkId = id
        )

        whenever(
            dateUtilsProvider.getRelativeDateTimeString(
                eq(context),
                any(),
                eq(DateUtils.DAY_IN_MILLIS),
                eq(DateUtils.WEEK_IN_MILLIS),
                eq(0)
            )
        ).thenReturn(date)

        val actual = viewStateMapper.map(fork)

        assertEquals(expected, actual)
    }


    private companion object {
        const val id = "id"
        const val avatarUrl = "foo/bar"
        const val ownerLogin = "Foo Bar"
        const val date = "today"

        val owner = User(ownerLogin, avatarUrl)

        val fork = Fork(id, Calendar.getInstance(), owner)
    }
}