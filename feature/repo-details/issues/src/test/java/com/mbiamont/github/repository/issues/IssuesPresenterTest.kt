package com.mbiamont.github.repository.issues

import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState
import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.domain.entity.User
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

import org.junit.Before
import java.util.*

class IssuesPresenterTest {

    private val viewStateMapper: IIssuesViewStateMapper = mock()
    private val view: IIssuesView = mock()

    private lateinit var presenter: IssuesPresenter

    @Before
    fun setUp() {
        presenter = IssuesPresenter(viewStateMapper)
        presenter.onAttachView(view)
    }

    @Test
    fun displayIssues() {
        whenever(viewStateMapper.map(issue)).thenReturn(viewState)

        presenter.displayIssues(listOf(issue))

        verify(view).displayIssueList(listOf(viewState))
    }

    @Test
    fun displayIssues_Detached() {
        presenter.onDetachView()
        whenever(viewStateMapper.map(issue)).thenReturn(viewState)

        presenter.displayIssues(listOf(issue))

        verify(view, never()).displayIssueList(any())
    }

    @Test
    fun displayTimeSerieProgress() {
        val viewState = ProgressViewState(true)
        whenever(viewStateMapper.map(true)).thenReturn(viewState)

        presenter.displayTimeSerieProgress(true)
        verify(view).displayTimeSerieProgress(viewState)
    }

    @Test
    fun displayTimeSerieProgress_Detached() {
        presenter.onDetachView()
        val viewState = ProgressViewState(true)
        whenever(viewStateMapper.map(true)).thenReturn(viewState)

        presenter.displayTimeSerieProgress(true)
        verify(view, never()).displayTimeSerieProgress(any())
    }

    @Test
    fun displayTimeSerie() {
        val viewState = TimeSerieViewState(arrayOf(1, 2, 3))
        whenever(viewStateMapper.map(arrayOf(1, 2, 3))).thenReturn(viewState)

        presenter.displayTimeSerie(arrayOf(1, 2, 3))
        verify(view).displayTimeSerie(viewState)
    }

    @Test
    fun displayTimeSerie_Detached() {
        presenter.onDetachView()
        val viewState = TimeSerieViewState(arrayOf(1, 2, 3))
        whenever(viewStateMapper.map(arrayOf(1, 2, 3))).thenReturn(viewState)

        presenter.displayTimeSerie(arrayOf(1, 2, 3))
        verify(view, never()).displayTimeSerie(any())
    }

    @Test
    fun displayFetchIssuesError() {
        presenter.displayFetchIssuesError()

        verify(view).displayErrorMessage(R.string.errorsFetchingIssues)
    }

    @Test
    fun displayFetchIssuesError_Detached() {
        presenter.onDetachView()
        presenter.displayFetchIssuesError()

        verify(view, never()).displayErrorMessage(any())
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