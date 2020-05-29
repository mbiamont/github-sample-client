package com.mbiamont.github.main

import com.mbiamont.github.domain.feature.main.FetchUserPublicRepositoriesUseCase

class MainController(
    private val fetchUserPublicRepositoriesUseCase: FetchUserPublicRepositoriesUseCase
) : IMainController {

    override suspend fun onViewReady() = fetchUserPublicRepositoriesUseCase.fetchUserPublicRepositories()
}