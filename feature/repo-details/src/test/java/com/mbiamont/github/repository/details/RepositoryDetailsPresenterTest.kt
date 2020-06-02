package com.mbiamont.github.repository.details

import com.mbiamont.github.domain.entity.Language
import com.mbiamont.github.domain.entity.RepositoryDetails
import com.mbiamont.github.domain.entity.User
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

import org.junit.Before

class RepositoryDetailsPresenterTest {

    private val viewStateMapper: IRepositoryDetailsViewStateMapper = mock()
    private val view: IRepositoryDetailsView = mock()

    private lateinit var presenter: RepositoryDetailsPresenter

    @Before
    fun setup(){
        presenter = RepositoryDetailsPresenter(viewStateMapper)
        presenter.onAttachView(view)
    }

    @Test
    fun displayRepositoryDetails() {
        whenever(viewStateMapper.map(entity)).thenReturn(viewState)

        presenter.displayRepositoryDetails(entity)
        verify(view).displayRepositoryDetails(viewState)
    }

    @Test
    fun displayRepositoryDetails_Detached() {
        presenter.onDetachView()
        whenever(viewStateMapper.map(entity)).thenReturn(viewState)

        presenter.displayRepositoryDetails(entity)
        verify(view, never()).displayRepositoryDetails(any())
    }

    @Test
    fun displayFetchRepositoryDetailsError() {
        presenter.displayFetchRepositoryDetailsError()

        verify(view).displayErrorMessage(R.string.errorsFetchRepositoryDetails)
    }

    @Test
    fun displayFetchRepositoryDetailsError_Detached() {
        presenter.onDetachView()
        presenter.displayFetchRepositoryDetailsError()

        verify(view, never()).displayErrorMessage(any())
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
        const val color = 123098
        const val colorStr = "foobaz"

        val mainLanguageViewState = MainLanguageViewState(languageName, color)

        val viewState = RepositoryDetailsViewState(
            ownerName = ownerName,
            ownerAvatarUrl = avatarUrl,
            repositoryName = repositoryName,
            repositoryDescription = repositoryDescription,
            mainLanguage = mainLanguageViewState,
            starCountLabel = starCount.toString(),
            pullRequestCountLabel = pullRequestCount.toString(),
            forkCountLabel = forkCount.toString()
        )

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