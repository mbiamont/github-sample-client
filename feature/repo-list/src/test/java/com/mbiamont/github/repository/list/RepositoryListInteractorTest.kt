package com.mbiamont.github.repository.list

import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.domain.datasource.IRepositoryDataSource
import com.mbiamont.github.domain.entity.Language
import com.mbiamont.github.domain.entity.RepositoryExtract
import com.mbiamont.github.domain.entity.User
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.lang.RuntimeException

class RepositoryListInteractorTest {

    private val repositoryDataSource: IRepositoryDataSource = mock()
    private val presenter: IRepositoryListPresenter = mock()

    private lateinit var interactor: RepositoryListInteractor

    @Before
    fun setUp() {
        interactor = RepositoryListInteractor(repositoryDataSource, presenter)
    }

    @Test
    fun fetchUserPublicRepositories() = runBlockingTest {
        val repos = mutableListOf(repo1, repo2)
        val publicRepositories = PaginatedList(repos, true, 3, "BAR_foo")
        whenever(repositoryDataSource.getUserPublicRepositories(null)).thenReturn(success(publicRepositories))

        interactor.fetchUserPublicRepositories()

        verify(presenter).displayRepositoryExtracts(repos)
        assertEquals(publicRepositories, interactor.repositories)
    }

    @Test
    fun fetchUserPublicRepositories_AlreadyData() = runBlockingTest {
        interactor.repositories = PaginatedList(mutableListOf(repo1, repo2), true, 3, "BAR_foo")

        val newRepos = PaginatedList(mutableListOf(repo3), true, 3, "BAR_foo")
        whenever(repositoryDataSource.getUserPublicRepositories("BAR_foo")).thenReturn(success(newRepos))
        val expected = PaginatedList(mutableListOf(repo1, repo2, repo3), true, 3, "BAR_foo")

        interactor.fetchUserPublicRepositories()

        verify(presenter).displayRepositoryExtracts(mutableListOf(repo1, repo2, repo3))


        assertEquals(expected, interactor.repositories)
    }

    @Test
    fun fetchUserPublicRepositories_Error() = runBlockingTest {
        whenever(repositoryDataSource.getUserPublicRepositories(null)).thenReturn(failure(RuntimeException("boom")))

        interactor.fetchUserPublicRepositories()

        verify(presenter, never()).displayRepositoryExtracts(any())
        verify(presenter).displayFetchRepositoriesError()
    }

    private companion object {
        val owner = User("foo", "bar")
        val language = Language("FOO", "#123456")

        val repo1 = RepositoryExtract("repo_1", owner, 901, language)
        val repo2 = RepositoryExtract("repo_2", owner, 902, language)
        val repo3 = RepositoryExtract("repo_3", owner, 903, language)

    }
}