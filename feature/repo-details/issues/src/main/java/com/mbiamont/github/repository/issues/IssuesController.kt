package com.mbiamont.github.repository.issues

import com.mbiamont.github.domain.feature.repository.details.issues.FetchRepositoryIssuesUseCase

class IssuesController(
    private val fetchRepositoryIssuesUseCase: FetchRepositoryIssuesUseCase
) : IIssuesController {

    override suspend fun onViewReady(repositoryName: String, ownerLogin: String) =
        fetchRepositoryIssuesUseCase.fetchRepositoryIssues(repositoryName, ownerLogin)
}