package com.mbiamont.github.domain.datasource

import com.mbiamont.github.core.Monad
import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.domain.entity.PullRequest
import java.util.*

interface IPullRequestDataSource {

    suspend fun getRepositoryPullRequests(
        repositoryName: String,
        ownerLogin: String,
        afterCursor: String?
    ): Monad<PaginatedList<PullRequest>>
}