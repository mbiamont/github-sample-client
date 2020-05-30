package com.mbiamont.github.repository.pullrequests

import com.mbiamont.github.domain.datasource.IIssueDataSource
import com.mbiamont.github.domain.feature.repository.details.pullrequests.FetchRepositoryPullRequestsUseCase

class PullRequestsInteractor(
    private val issueDataSource: IIssueDataSource,
    private val presenter: IPullRequestsPresenter
) : FetchRepositoryPullRequestsUseCase {

    override suspend fun fetchRepositoryPullRequests(repositoryName: String, ownerLogin: String) {
        TODO("Not yet implemented")
    }

}