package com.mbiamont.github.datasource

import com.mbiamont.github.datasource.service.IRemoteIssueService
import com.mbiamont.github.domain.datasource.IIssueDataSource
import java.util.*

class IssueDataSource(
    private val remoteIssueService: IRemoteIssueService
) : IIssueDataSource {

    override suspend fun getRepositoryIssues(repositoryName: String, ownerLogin: String, afterCursor: String?) =
        remoteIssueService.getRepositoryIssues(repositoryName, ownerLogin, afterCursor)
}