package com.mbiamont.github.repository.list

import com.mbiamont.github.core.test.coroutine.MockCoroutineContextProvider
import com.mbiamont.github.core.test.rule.AsyncLiveDataRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class RepositoryListViewModelTest {

    @get:Rule
    var rule: TestRule = AsyncLiveDataRule()

    private val controller: IRepositoryListController = mock()
    private val presenter: IRepositoryListPresenter = mock()

    private lateinit var viewModel: RepositoryListViewModel

    @Before
    fun setUp() {
        viewModel = RepositoryListViewModel(MockCoroutineContextProvider, controller, presenter)
    }

    @Test
    fun onViewReady() = runBlockingTest {
        viewModel.onViewReady().join()

        verify(controller).onViewReady()
    }

    @Test
    fun onRepositoriesScrolled() = runBlockingTest {
        viewModel.onRepositoriesScrolled().join()

        verify(controller).onRepositoriesScrolled()
    }

    @Test
    fun onRepositoryExtractClicked() = runBlockingTest {
        viewModel.onRepositoryExtractClicked("foo", "bar").join()

        verify(controller).onRepositoryClicked("foo", "bar")
    }

    @Test
    fun displayRepositoryExtractList() {
        val viewState = RepositoryExtractViewState("foo.bar", "84", LanguageViewState("baz", 123456), "foobar")

        viewModel.displayRepositoryExtractList(listOf(viewState))

        assertEquals(listOf(viewState), viewModel.repositoriesLiveData.value)
    }

    @Test
    fun displayErrorMessage() {
        viewModel.displayErrorMessage(42)

        assertEquals(42, viewModel.errorMessageLiveData.value)
    }
}