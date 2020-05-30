package com.mbiamont.github.repository.pullrequests

import com.mbiamont.github.domain.feature.repository.details.issues.FetchRepositoryIssuesUseCase

class PullRequestController(
    private val fetchRepositoryIssuesUseCase: FetchRepositoryIssuesUseCase
) : IPullRequestController {

    override suspend fun onViewReady(repositoryName: String, ownerLogin: String) =
        fetchRepositoryIssuesUseCase.fetchRepositoryIssues(repositoryName, ownerLogin)
}