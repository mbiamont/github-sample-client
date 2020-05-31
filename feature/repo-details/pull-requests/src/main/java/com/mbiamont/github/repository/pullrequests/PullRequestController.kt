package com.mbiamont.github.repository.pullrequests

import com.mbiamont.github.domain.feature.repository.details.pullrequests.FetchRepositoryPullRequestsUseCase

class PullRequestController(
    private val fetchRepositoryIssuesUseCase: FetchRepositoryPullRequestsUseCase
) : IPullRequestController {

    override suspend fun onViewReady(repositoryName: String, ownerLogin: String) =
        fetchRepositoryIssuesUseCase.fetchRepositoryPullRequests(repositoryName, ownerLogin)
}