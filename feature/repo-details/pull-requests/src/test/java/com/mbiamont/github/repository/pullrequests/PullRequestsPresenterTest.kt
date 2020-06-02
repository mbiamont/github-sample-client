package com.mbiamont.github.repository.pullrequests

import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState
import com.mbiamont.github.domain.entity.PullRequest
import com.mbiamont.github.domain.entity.User
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test

import java.util.*

class PullRequestsPresenterTest {

    private val viewStateMapper: IPullRequestsViewStateMapper = mock()
    private val view: IPullRequestsView = mock()

    private lateinit var presenter: PullRequestsPresenter

    @Before
    fun setUp() {
        presenter = PullRequestsPresenter(viewStateMapper)
        presenter.onAttachView(view)
    }

    @Test
    fun displayPullRequests() {
        whenever(viewStateMapper.map(pullRequest)).thenReturn(viewState)

        presenter.displayPullRequests(listOf(pullRequest))

        verify(view).displayPullRequestList(listOf(viewState))
    }

    @Test
    fun displayPullRequests_Detached() {
        presenter.onDetachView()
        whenever(viewStateMapper.map(pullRequest)).thenReturn(viewState)

        presenter.displayPullRequests(listOf(pullRequest))

        verify(view, never()).displayPullRequestList(any())
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
    fun displayFetchPullRequestsError() {
        presenter.displayFetchPullRequestsError()

        verify(view).displayErrorMessage(R.string.errorsFetchingPullRequests)
    }

    @Test
    fun displayFetchPullRequestsError_Detached() {
        presenter.onDetachView()
        presenter.displayFetchPullRequestsError()

        verify(view, never()).displayErrorMessage(any())
    }

    private companion object {
        const val color = 234
        const val avatarUrl = "foo://bar"
        const val ownerLogin = "Foo bar"
        const val dateLabel = "today"
        const val title = "FOO? BAR!"
        const val commentsCount = 239
        const val id = "id_083"


        val viewState = PullRequestViewState(
            indexColor = color,
            ownerAvatarUrl = avatarUrl,
            ownerLogin = ownerLogin,
            title = title,
            dateLabel = dateLabel,
            commentCountLabel = commentsCount.toString(),
            pullRequestId = id
        )

        val pullRequest = PullRequest(
            id = id,
            title = title,
            state = PullRequest.State.MERGED,
            createdAt = Calendar.getInstance(),
            author = User(ownerLogin, avatarUrl),
            commentsCount = commentsCount
        )
    }
}