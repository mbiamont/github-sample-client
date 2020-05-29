package com.mbiamont.github.datasource

import com.mbiamont.github.datasource.service.IRemoteIssueService
import com.mbiamont.github.domain.datasource.IIssueDataSource

class IssueDataSource(
    private val remoteIssueService: IRemoteIssueService
) : IIssueDataSource {

    override suspend fun getRepositoryIssues(repositoryName: String, ownerLogin: String) =
        remoteIssueService.getRepositoryIssues(repositoryName, ownerLogin)
}