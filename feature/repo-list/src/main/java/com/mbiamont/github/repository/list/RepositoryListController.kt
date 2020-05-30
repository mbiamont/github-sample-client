package com.mbiamont.github.repository.list

import com.mbiamont.github.domain.feature.repository.list.FetchUserPublicRepositoriesUseCase

class RepositoryListController(
    private val fetchUserPublicRepositoriesUseCase: FetchUserPublicRepositoriesUseCase
) : IRepositoryListController {

    override suspend fun onViewReady() = fetchUserPublicRepositoriesUseCase.fetchUserPublicRepositories()
}