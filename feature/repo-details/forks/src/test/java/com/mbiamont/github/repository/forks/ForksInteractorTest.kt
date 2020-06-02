package com.mbiamont.github.repository.forks

import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.domain.datasource.IForkDataSource
import com.mbiamont.github.domain.entity.Fork
import com.mbiamont.github.domain.entity.User
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.lang.RuntimeException
import java.util.*

class ForksInteractorTest {

    private val presenter: IForksPresenter = mock()
    private val forkDataSource: IForkDataSource = mock()

    private lateinit var interactor: ForksInteractor

    @Before
    fun setUp() {
        interactor = ForksInteractor(presenter, forkDataSource)
    }

    @Test
    fun fetchRepositoryForks() = runBlockingTest {
        whenever(forkDataSource.getRepositoryForks("foo", "bar", null)).thenReturn(success(paginatedListA))
        whenever(forkDataSource.getRepositoryForks("foo", "bar", "cursor-1")).thenReturn(success(paginatedListB))
        val argumentCaptor = argumentCaptor<List<Fork>>()

        interactor.fetchRepositoryForks("foo", "bar")

        verify(presenter, times(2)).displayTimeSerieProgress(true)
        verify(presenter, times(2)).displayForks(argumentCaptor.capture())
        verify(presenter).displayTimeSerieProgress(false)
        verify(presenter).displayTimeSerie(expected)

        assertEquals(listTotal, argumentCaptor.secondValue)
    }

    @Test
    fun fetchRepositoryForks_Error() = runBlockingTest {
        whenever(forkDataSource.getRepositoryForks("foo", "bar", null)).thenReturn(failure(RuntimeException("boom")))

        interactor.fetchRepositoryForks("foo", "bar")

        verify(presenter).displayTimeSerieProgress(true)
        verify(presenter, never()).displayForks(any())
        verify(presenter).displayTimeSerieProgress(false)
        verify(presenter).displayFetchForksError()
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

        val owner = User("foo", "bar")

        val fork1 = Fork("fork1", today, owner)
        val fork2 = Fork("fork2", twoWeeksAgo, owner)
        val fork2Bis = Fork("fork2Bis", twoWeeksAgo, owner)
        val fork3 = Fork("fork3", threeWeeksAgo, owner)

        val tooOldFork = Fork("tooOldFork", twoYearAgo, owner)
        val futureFork = Fork("futureFork", nextWeek, owner)

        val listA = mutableListOf(futureFork, fork1, fork2Bis)
        val listB = mutableListOf(fork2, fork3, tooOldFork)

        val listTotal = mutableListOf(futureFork, fork1, fork2Bis, fork2, fork3, tooOldFork)

        val paginatedListA = PaginatedList(listA, true, 6, "cursor-1")
        val paginatedListB = PaginatedList(listB, false, 6, "cursor-2")
    }
}