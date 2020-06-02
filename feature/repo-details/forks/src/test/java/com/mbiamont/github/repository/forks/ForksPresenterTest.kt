package com.mbiamont.github.repository.forks

import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState
import com.mbiamont.github.domain.entity.Fork
import com.mbiamont.github.domain.entity.User
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test

import java.util.*

class ForksPresenterTest {

    private val viewStateMapper: IForksViewStateMapper = mock()
    private val view: IForksView = mock()

    private lateinit var presenter: ForksPresenter

    @Before
    fun setUp() {
        presenter = ForksPresenter(viewStateMapper)
        presenter.onAttachView(view)
    }

    @Test
    fun displayForks() {
        whenever(viewStateMapper.map(fork)).thenReturn(viewState)

        presenter.displayForks(listOf(fork))

        verify(view).displayForkList(listOf(viewState))
    }

    @Test
    fun displayForks_Detached() {
        presenter.onDetachView()
        whenever(viewStateMapper.map(fork)).thenReturn(viewState)

        presenter.displayForks(listOf(fork))

        verify(view, never()).displayForkList(any())
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
    fun displayFetchForksError() {
        presenter.displayFetchForksError()

        verify(view).displayErrorMessage(R.string.errorsFetchingForks)
    }

    @Test
    fun displayFetchForksError_Detached() {
        presenter.onDetachView()
        presenter.displayFetchForksError()

        verify(view, never()).displayErrorMessage(any())
    }


    private companion object {
        const val id = "id"
        const val avatarUrl = "foo/bar"
        const val ownerLogin = "Foo Bar"
        const val date = "today"

        val owner = User(ownerLogin, avatarUrl)

        val fork = Fork(id, Calendar.getInstance(), owner)
        val viewState = ForkViewState(
            ownerAvatarUrl = avatarUrl,
            ownerLogin = ownerLogin,
            dateLabel = date,
            forkId = id
        )
    }
}