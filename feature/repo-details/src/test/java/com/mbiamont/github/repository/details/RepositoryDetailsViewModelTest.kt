package com.mbiamont.github.repository.details

import android.os.Bundle
import com.mbiamont.github.core.CoroutineContextProvider
import com.mbiamont.github.core.test.coroutine.MockCoroutineContextProvider
import com.mbiamont.github.core.test.rule.AsyncLiveDataRule
import com.mbiamont.github.domain.navigation.EXTRA_OWNER_LOGIN
import com.mbiamont.github.domain.navigation.EXTRA_REPO_NAME
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class RepositoryDetailsViewModelTest {

    @get:Rule
    var rule: TestRule = AsyncLiveDataRule()

    private val controller: IRepositoryDetailsController = mock()
    private val presenter: IRepositoryDetailsPresenter = mock()

    private lateinit var viewModel: RepositoryDetailsViewModel

    @Before
    fun setup() {
        viewModel = RepositoryDetailsViewModel(MockCoroutineContextProvider, controller, presenter)
        verify(presenter).onAttachView(viewModel)
    }


    @Test
    fun onViewReady() = runBlockingTest {
        val bundle: Bundle = mock()
        whenever(bundle.getString(EXTRA_REPO_NAME)).thenReturn("foo")
        whenever(bundle.getString(EXTRA_OWNER_LOGIN)).thenReturn("bar")

        viewModel.onViewReady(bundle).join()

        verify(controller).onViewReady("foo", "bar")
    }

    @Test
    fun onViewReady_InvalidExtras() = runBlockingTest {
        val bundle: Bundle = mock()
        whenever(bundle.getString(EXTRA_REPO_NAME)).thenReturn(null)
        whenever(bundle.getString(EXTRA_OWNER_LOGIN)).thenReturn("bar")

        viewModel.onViewReady(bundle).join()

        verify(controller, never()).onViewReady(any(), any())
    }

    @Test
    fun displayRepositoryDetails() {
        viewModel.displayRepositoryDetails(viewState)

        assertEquals(viewState, viewModel.repositoryDetailsLiveData.value)
    }

    @Test
    fun displayErrorMessage() {
        viewModel.displayErrorMessage(23)

        assertEquals(23, viewModel.errorMessageLiveData.value)
    }

    private companion object {
        val viewState = RepositoryDetailsViewState(
            ownerName = "Foo",
            ownerAvatarUrl = "bar_foo",
            repositoryName = "FOO_BAR",
            repositoryDescription = "BARBAR",
            mainLanguage = null,
            starCountLabel = "0",
            pullRequestCountLabel = "1",
            forkCountLabel = "2"
        )
    }
}