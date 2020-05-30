package com.mbiamont.github.repository.list

import com.mbiamont.github.core.android.INavigator
import com.mbiamont.github.domain.feature.repository.list.FetchUserPublicRepositoriesUseCase
import com.mbiamont.github.domain.navigation.EXTRA_REPO_NAME
import com.mbiamont.github.domain.navigation.EXTRA_OWNER_LOGIN
import com.mbiamont.github.domain.navigation.REPO_DETAILS

class RepositoryListController(
    private val fetchUserPublicRepositoriesUseCase: FetchUserPublicRepositoriesUseCase,
    private val navigator: INavigator
) : IRepositoryListController {

    override suspend fun onViewReady() = fetchUserPublicRepositoriesUseCase.fetchUserPublicRepositories()

    override suspend fun onRepositoryClicked(repositoryName: String, ownerLogin: String) =
        navigator.navigateTo(REPO_DETAILS, mapOf(EXTRA_REPO_NAME to repositoryName, EXTRA_OWNER_LOGIN to ownerLogin))
}