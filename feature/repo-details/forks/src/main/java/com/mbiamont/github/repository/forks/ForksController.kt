package com.mbiamont.github.repository.forks

import com.mbiamont.github.domain.feature.repository.details.forks.FetchRepositoryForksUseCase

class ForksController(
    private val fetchRepositoryIssuesUseCase: FetchRepositoryForksUseCase
) : IForksController {

    override suspend fun onViewReady(repositoryName: String, ownerLogin: String) =
        fetchRepositoryIssuesUseCase.fetchRepositoryForks(repositoryName, ownerLogin)
}