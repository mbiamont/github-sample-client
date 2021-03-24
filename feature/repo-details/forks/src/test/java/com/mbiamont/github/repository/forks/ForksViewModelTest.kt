package com.mbiamont.github.repository.forks

import android.os.BaseBundle
import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState
import com.mbiamont.github.core.test.coroutine.MockCoroutineContextProvider
import com.mbiamont.github.core.test.rule.AsyncLiveDataRule
import com.mbiamont.github.domain.navigation.EXTRA_OWNER_LOGIN
import com.mbiamont.github.domain.navigation.EXTRA_REPO_NAME
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

@ExperimentalCoroutinesApi
class ForksViewModelTest : KoinTest {

    private val controller: IForksController = mock()
    private val presenter: IForksPresenter = mock()

    @get:Rule
    var rule: TestRule = AsyncLiveDataRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                scope<ForksViewModel> {
                    scoped { controller }
                    scoped { presenter }
                }
            }
        )
    }

    private lateinit var viewModel: ForksViewModel

    @Before
    fun setup() {
        viewModel = ForksViewModel(MockCoroutineContextProvider)

        verify(presenter).onAttachView(viewModel)
    }

    @Test
    fun onViewReady() = runBlockingTest {
        val bundle: BaseBundle = mock()
        whenever(bundle.getString(EXTRA_REPO_NAME)).thenReturn("foo")
        whenever(bundle.getString(EXTRA_OWNER_LOGIN)).thenReturn("bar")

        viewModel.onViewReady(bundle).join()

        verify(controller).onViewReady("foo", "bar")
    }

    @Test
    fun onViewReady_AlreadyInitialized() = runBlockingTest {
        viewModel.initialized = true

        val bundle: BaseBundle = mock()
        whenever(bundle.getString(EXTRA_REPO_NAME)).thenReturn("foo")
        whenever(bundle.getString(EXTRA_OWNER_LOGIN)).thenReturn("bar")

        viewModel.onViewReady(bundle).join()

        verify(controller, never()).onViewReady(any(), any())
    }

    @Test
    fun onViewReady_InvalidExtras() = runBlockingTest {
        viewModel.initialized = true

        val bundle: BaseBundle = mock()
        whenever(bundle.getString(EXTRA_REPO_NAME)).thenReturn(null)
        whenever(bundle.getString(EXTRA_OWNER_LOGIN)).thenReturn("bar")

        viewModel.onViewReady(bundle).join()

        verify(controller, never()).onViewReady(any(), any())
    }

    @Test
    fun displayForkList() {
        val forksViewState = listOf(viewState)
        viewModel.displayForkList(forksViewState)

        assertEquals(forksViewState, viewModel.forkListLiveData.value)
    }

    @Test
    fun displayTimeSerieProgress() {
        val progressViewState = ProgressViewState(true)

        viewModel.displayTimeSerieProgress(progressViewState)

        assertEquals(progressViewState, viewModel.progressLiveData.value)
    }

    @Test
    fun displayTimeSerie() {
        val timeSerieViewState = TimeSerieViewState(arrayOf(13))

        viewModel.displayTimeSerie(timeSerieViewState)

        assertEquals(timeSerieViewState, viewModel.timeSerieLiveData.value)
    }

    @Test
    fun displayErrorMessage() {
        viewModel.displayErrorMessage(23)

        assertEquals(23, viewModel.errorMessageLiveData.value)
    }

    private companion object {
        const val id = "id"
        const val avatarUrl = "foo/bar"
        const val ownerLogin = "Foo Bar"
        const val date = "today"

        val viewState = ForkViewState(
            ownerAvatarUrl = avatarUrl,
            ownerLogin = ownerLogin,
            dateLabel = date,
            forkId = id
        )
    }
}