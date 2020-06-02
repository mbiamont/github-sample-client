package com.mbiamont.github.repository.issues

import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.domain.datasource.IIssueDataSource
import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.domain.entity.User
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.lang.RuntimeException
import java.util.*

class IssuesInteractorTest {

    private val presenter: IIssuesPresenter = mock()
    private val issueDataSource: IIssueDataSource = mock()

    private lateinit var interactor: IssuesInteractor

    @Before
    fun setUp() {
        interactor = IssuesInteractor(issueDataSource, presenter)
    }

    @Test
    fun fetchRepositoryIssues() = runBlockingTest {
        whenever(issueDataSource.getRepositoryIssues("foo", "bar", null)).thenReturn(success(paginatedListA))
        whenever(issueDataSource.getRepositoryIssues("foo", "bar", "cursor-1")).thenReturn(success(paginatedListB))
        val argumentCaptor = argumentCaptor<List<Issue>>()

        interactor.fetchRepositoryIssues("foo", "bar")

        verify(presenter, times(2)).displayTimeSerieProgress(true)
        verify(presenter, times(2)).displayIssues(argumentCaptor.capture())
        verify(presenter).displayTimeSerieProgress(false)
        verify(presenter).displayTimeSerie(expected)

        assertEquals(listTotal, argumentCaptor.secondValue)
    }

    @Test
    fun fetchRepositoryIssues_Error() = runBlockingTest {
        whenever(issueDataSource.getRepositoryIssues("foo", "bar", null)).thenReturn(failure(RuntimeException("boom")))

        interactor.fetchRepositoryIssues("foo", "bar")

        verify(presenter).displayTimeSerieProgress(true)
        verify(presenter, never()).displayIssues(any())
        verify(presenter).displayTimeSerieProgress(false)
        verify(presenter).displayFetchIssuesError()
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

        val issue = Issue(
            id = "",
            title = "title",
            state = Issue.State.OPEN,
            createdAt = Calendar.getInstance(),
            author = User("foo", "bar"),
            commentsCount = 23
        )

        val issue1 = issue.copy(createdAt = today, id = "issue1")
        val issue2 = issue.copy(createdAt = twoWeeksAgo, id = "issue2")
        val issue2Bis = issue.copy(createdAt = twoWeeksAgo, id = "issue2Bis")
        val issue3 = issue.copy(createdAt = threeWeeksAgo, id = "issue3")

        val issueTooOld = issue.copy(createdAt = twoYearAgo, id = "issueTooOld")
        val issueFuture = issue.copy(createdAt = nextWeek, id = "issueFuture")

        val listA = mutableListOf(issueFuture, issue1, issue2Bis)
        val listB = mutableListOf(issue2, issue3, issueTooOld)

        val listTotal = mutableListOf(issueFuture, issue1, issue2Bis, issue2, issue3, issueTooOld)

        val paginatedListA = PaginatedList(listA, true, 6, "cursor-1")
        val paginatedListB = PaginatedList(listB, false, 6, "cursor-2")
    }
}