package com.mbiamont.github.repository.pullrequests

import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.domain.datasource.IIssueDataSource
import com.mbiamont.github.domain.datasource.IPullRequestDataSource
import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.domain.entity.PullRequest
import com.mbiamont.github.domain.entity.User
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.lang.RuntimeException
import java.util.*

class PullRequestsInteractorTest {

    private val presenter: IPullRequestsPresenter = mock()
    private val pullRequestDataSource: IPullRequestDataSource = mock()

    private lateinit var interactor: PullRequestsInteractor

    @Before
    fun setUp() {
        interactor = PullRequestsInteractor(presenter, pullRequestDataSource)
    }

    @Test
    fun fetchRepositoryPullRequests() = runBlockingTest {
        whenever(pullRequestDataSource.getRepositoryPullRequests("foo", "bar", null)).thenReturn(success(paginatedListA))
        whenever(pullRequestDataSource.getRepositoryPullRequests("foo", "bar", "cursor-1")).thenReturn(success(paginatedListB))
        val argumentCaptor = argumentCaptor<List<PullRequest>>()

        interactor.fetchRepositoryPullRequests("foo", "bar")

        verify(presenter, times(2)).displayTimeSerieProgress(true)
        verify(presenter, times(2)).displayPullRequests(argumentCaptor.capture())
        verify(presenter).displayTimeSerieProgress(false)
        verify(presenter).displayTimeSerie(expected)

        assertEquals(listTotal, argumentCaptor.secondValue)
    }

    @Test
    fun fetchRepositoryPullRequests_Error() = runBlockingTest {
        whenever(pullRequestDataSource.getRepositoryPullRequests("foo", "bar", null)).thenReturn(failure(RuntimeException("boom")))

        interactor.fetchRepositoryPullRequests("foo", "bar")

        verify(presenter).displayTimeSerieProgress(true)
        verify(presenter, never()).displayPullRequests(any())
        verify(presenter).displayTimeSerieProgress(false)
        verify(presenter).displayFetchPullRequestsError()
    }

    private companion object {
        val expected = Array(52) { 0 }.apply {
            this[51] = 1
            this[49] = 2
            this[48] = 1
        }

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

        val pullRequest = PullRequest(
            id = "",
            title = "title",
            state = PullRequest.State.CLOSED,
            createdAt = Calendar.getInstance(),
            author = User("foo", "bar"),
            commentsCount = 23
        )

        val pullRequest1 = pullRequest.copy(createdAt = today, id = "pr1")
        val pullRequest2 = pullRequest.copy(createdAt = twoWeeksAgo, id = "pr2")
        val pullRequest2Bis = pullRequest.copy(createdAt = twoWeeksAgo, id = "pr2Bis")
        val pullRequest3 = pullRequest.copy(createdAt = threeWeeksAgo, id = "pr3")

        val pullRequestTooOld = pullRequest.copy(createdAt = twoYearAgo, id = "prTooOld")
        val pullRequestFuture = pullRequest.copy(createdAt = nextWeek, id = "prFuture")

        val listA = mutableListOf(pullRequestFuture, pullRequest1, pullRequest2Bis)
        val listB = mutableListOf(pullRequest2, pullRequest3, pullRequestTooOld)

        val listTotal = mutableListOf(pullRequestFuture, pullRequest1, pullRequest2Bis, pullRequest2, pullRequest3, pullRequestTooOld)

        val paginatedListA = PaginatedList(listA, true, 6, "cursor-1")
        val paginatedListB = PaginatedList(listB, false, 6, "cursor-2")
    }
}