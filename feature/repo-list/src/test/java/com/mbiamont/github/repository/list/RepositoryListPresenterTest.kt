package com.mbiamont.github.repository.list

import com.mbiamont.github.domain.entity.Language
import com.mbiamont.github.domain.entity.RepositoryExtract
import com.mbiamont.github.domain.entity.User
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class RepositoryListPresenterTest {

    private val viewStateMapper: IRepositoryListViewStateMapper = mock()
    private val view: IRepositoryListView = mock()
    private lateinit var presenter: RepositoryListPresenter


    @Before
    fun setup() {
        presenter = RepositoryListPresenter(viewStateMapper)
        presenter.onAttachView(view)
    }

    @Test
    fun displayRepositoryExtracts() {
        val extract = RepositoryExtract("foo", User("foobar", "bar"), 84, Language("baz", "#123456"))
        val viewState = RepositoryExtractViewState("foo", "84", LanguageViewState("baz", 123456), "foobar")

        whenever(viewStateMapper.map(extract)).thenReturn(viewState)

        presenter.displayRepositoryExtracts(listOf(extract))

        verify(view).displayRepositoryExtractList(listOf(viewState))
    }

    @Test
    fun displayRepositoryExtracts_Detached() {
        val extract = RepositoryExtract("foo", User("foobar", "bar"), 84, Language("baz", "#123456"))
        val viewState = RepositoryExtractViewState("foo", "84", LanguageViewState("baz", 123456), "foobar")

        whenever(viewStateMapper.map(extract)).thenReturn(viewState)

        presenter.onDetachView()
        presenter.displayRepositoryExtracts(listOf(extract))

        verify(view, never()).displayRepositoryExtractList(any())
    }

    @Test
    fun displayFetchRepositoriesError() {
        presenter.displayFetchRepositoriesError()

        verify(view).displayErrorMessage(R.string.errorsFetchRepository)
    }

    @Test
    fun displayFetchRepositoriesError_Detached() {
        presenter.onDetachView()
        presenter.displayFetchRepositoriesError()

        verify(view, never()).displayErrorMessage(any())
    }
}