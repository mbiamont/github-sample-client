package com.mbiamont.github.repository.issues

import android.content.Context
import android.content.res.Resources
import android.text.format.DateUtils
import com.mbiamont.github.core.android.provider.IDateUtilsProvider
import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState
import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.domain.entity.User
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class IssuesViewStateMapperTest {

    private val resources: Resources = mock()
    private val context: Context = mock()
    private val dateUtilsProvider: IDateUtilsProvider = mock()

    private lateinit var viewStateMapper: IssuesViewStateMapper

    @Before
    fun setUp() {
        viewStateMapper = IssuesViewStateMapper(context, dateUtilsProvider)
        whenever(context.resources).thenReturn(resources)
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
    fun mapIssueViewState() {
        whenever(resources.getColor(R.color.green)).thenReturn(color)
        whenever(
            dateUtilsProvider.getRelativeDateTimeString(
                eq(context),
                any(),
                eq(DateUtils.DAY_IN_MILLIS),
                eq(DateUtils.WEEK_IN_MILLIS),
                eq(0)
            )
        ).thenReturn(dateLabel)

        val actual = viewStateMapper.map(issue)

        assertEquals(viewState, actual)
    }

    private companion object {
        const val color = 234
        const val avatarUrl = "foo://bar"
        const val ownerLogin = "Foo bar"
        const val dateLabel = "today"
        const val title = "FOO? BAR!"
        const val commentsCount = 239
        const val issueId = "id_23"


        val viewState = IssueViewState(
            indexColor = color,
            ownerAvatarUrl = avatarUrl,
            ownerLogin = ownerLogin,
            title = title,
            dateLabel = dateLabel,
            commentCountLabel = commentsCount.toString(),
            issueId = issueId
        )

        val issue = Issue(
            id = issueId,
            title = title,
            state = Issue.State.OPEN,
            createdAt = Calendar.getInstance(),
            author = User(ownerLogin, avatarUrl),
            commentsCount = commentsCount
        )
    }
}