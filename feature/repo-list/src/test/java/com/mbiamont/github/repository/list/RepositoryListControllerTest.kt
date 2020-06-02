package com.mbiamont.github.repository.list

import com.mbiamont.github.core.android.INavigator
import com.mbiamont.github.domain.feature.repository.list.FetchUserPublicRepositoriesUseCase
import com.mbiamont.github.domain.navigation.EXTRA_OWNER_LOGIN
import com.mbiamont.github.domain.navigation.EXTRA_REPO_NAME
import com.mbiamont.github.domain.navigation.REPO_DETAILS
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RepositoryListControllerTest {

    private val fetchUserPublicRepositoriesUseCase: FetchUserPublicRepositoriesUseCase = mock()
    private val navigator: INavigator = mock()

    private lateinit var controller: RepositoryListController

    @Before
    fun setUp() {
        controller = RepositoryListController(fetchUserPublicRepositoriesUseCase, navigator)
    }

    @Test
    fun onViewReady() = runBlockingTest {
        controller.onViewReady()

        verify(fetchUserPublicRepositoriesUseCase).fetchUserPublicRepositories()
    }

    @Test
    fun onRepositoriesScrolled() = runBlockingTest {
        controller.onRepositoriesScrolled()

        verify(fetchUserPublicRepositoriesUseCase).fetchUserPublicRepositories()
    }

    @Test
    fun onRepositoryClicked() = runBlockingTest{
        controller.onRepositoryClicked("foo", "bar")

        verify(navigator).navigateTo(REPO_DETAILS, mapOf(EXTRA_REPO_NAME to "foo", EXTRA_OWNER_LOGIN to "bar"))
    }
}