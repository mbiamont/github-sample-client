package com.mbiamont.github.repository.details

import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.domain.datasource.IRepositoryDataSource
import com.mbiamont.github.domain.entity.Language
import com.mbiamont.github.domain.entity.RepositoryDetails
import com.mbiamont.github.domain.entity.User
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.lang.RuntimeException

class RepositoryDetailsInteractorTest {

    private val repositoryDataSource: IRepositoryDataSource = mock()
    private val presenter: IRepositoryDetailsPresenter = mock()

    private lateinit var interactor: RepositoryDetailsInteractor

    @Before
    fun setUp() {
        interactor = RepositoryDetailsInteractor(repositoryDataSource, presenter)
    }

    @Test
    fun fetchRepositoryDetails() = runBlockingTest {
        whenever(repositoryDataSource.getRepositoryWithNameAndOwner("foo", "bar")).thenReturn(success(entity))
        interactor.fetchRepositoryDetails("foo", "bar")

        verify(presenter).displayRepositoryDetails(entity)
    }

    @Test
    fun fetchRepositoryDetails_Error() = runBlockingTest {
        whenever(repositoryDataSource.getRepositoryWithNameAndOwner("foo", "bar")).thenReturn(failure(RuntimeException("boom")))
        interactor.fetchRepositoryDetails("foo", "bar")

        verify(presenter).displayFetchRepositoryDetailsError()
        verify(presenter, never()).displayRepositoryDetails(any())
    }

    private companion object {
        const val ownerName = "Foo.bar"
        const val avatarUrl = "(bar)foo"
        const val repositoryName = "bar/foo"
        const val repositoryDescription = "bar"
        const val starCount = 123
        const val pullRequestCount = 234
        const val forkCount = 345

        const val languageName = "foo"
        const val colorStr = "foobaz"

        val entity = RepositoryDetails(
            name = repositoryName,
            owner = User(ownerName, avatarUrl),
            description = repositoryDescription,
            starsCount = starCount,
            mainLanguage = Language(languageName, colorStr),
            issuesCount = 23,
            pullRequestsCount = pullRequestCount,
            forksCount = forkCount
        )
    }
}