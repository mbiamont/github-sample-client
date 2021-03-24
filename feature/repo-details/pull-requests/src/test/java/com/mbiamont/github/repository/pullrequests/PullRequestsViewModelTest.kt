package com.mbiamont.github.repository.pullrequests

import android.os.BaseBundle
import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState
import com.mbiamont.github.core.test.coroutine.MockCoroutineContextProvider
import com.mbiamont.github.core.test.rule.AsyncLiveDataRule
import com.mbiamont.github.domain.navigation.EXTRA_OWNER_LOGIN
import com.mbiamont.github.domain.navigation.EXTRA_REPO_NAME
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule
import org.koin.dsl.module
import org.koin.test.KoinTestRule

class PullRequestsViewModelTest {
    private val controller: IPullRequestController = mock()
    private val presenter: IPullRequestsPresenter = mock()

    @get:Rule
    var rule: TestRule = AsyncLiveDataRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                scope<PullRequestsViewModel> {
                    scoped { controller }
                    scoped { presenter }
                }
            }
        )
    }


    private lateinit var viewModel: PullRequestsViewModel

    @Before
    fun setup() {
        viewModel = PullRequestsViewModel(MockCoroutineContextProvider)

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
    fun displayPullRequestList() {
        val viewState = listOf(pullRequestViewState)

        viewModel.displayPullRequestList(viewState)


        assertEquals(viewState, viewModel.pullRequestsListLiveData.value)
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
        const val indexColor = 23
        const val avatarUrl = "foo_bar"
        const val ownerLogin = "bar_FOO"
        const val title = "FOO BAR"
        const val dateLabel = "today"
        const val commentLabel = "223"
        const val id = "id_1"

        val pullRequestViewState = PullRequestViewState(
            indexColor = indexColor,
            ownerAvatarUrl = avatarUrl,
            ownerLogin = ownerLogin,
            title = title,
            dateLabel = dateLabel,
            commentCountLabel = commentLabel,
            pullRequestId = id
        )
    }
}