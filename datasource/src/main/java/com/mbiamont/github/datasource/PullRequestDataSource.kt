package com.mbiamont.github.datasource

import com.mbiamont.github.datasource.service.IRemotePullRequestService
import com.mbiamont.github.domain.datasource.IPullRequestDataSource

class PullRequestDataSource(
    private val remotePullRequestService: IRemotePullRequestService
) : IPullRequestDataSource {

    override suspend fun getRepositoryPullRequests(repositoryName: String, ownerLogin: String, afterCursor: String?) =
        remotePullRequestService.getRepositoryPullRequests(repositoryName, ownerLogin, afterCursor)
}