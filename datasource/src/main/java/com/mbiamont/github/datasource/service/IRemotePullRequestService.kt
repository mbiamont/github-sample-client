package com.mbiamont.github.datasource.service

import com.mbiamont.github.core.Monad
import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.domain.entity.Fork
import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.domain.entity.PullRequest
import java.util.*

interface IRemotePullRequestService {

    suspend fun getRepositoryPullRequests(
        repositoryName: String,
        ownerLogin: String,
        afterCursor: String?
    ): Monad<PaginatedList<PullRequest>>
}