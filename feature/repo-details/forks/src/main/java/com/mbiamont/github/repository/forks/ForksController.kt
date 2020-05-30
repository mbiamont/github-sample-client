package com.mbiamont.github.repository.forks

import com.mbiamont.github.domain.feature.repository.details.issues.FetchRepositoryIssuesUseCase

class ForksController(
    private val fetchRepositoryIssuesUseCase: FetchRepositoryIssuesUseCase
) : IForksController {

    override suspend fun onViewReady(repositoryName: String, ownerLogin: String) =
        fetchRepositoryIssuesUseCase.fetchRepositoryIssues(repositoryName, ownerLogin)
}