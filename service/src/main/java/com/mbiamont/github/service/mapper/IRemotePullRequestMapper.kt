package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.PullRequest
import com.mbiamont.github.service.graphql.FetchRepositoryPullRequestsQuery

interface IRemotePullRequestMapper {

    fun map(pullRequest: FetchRepositoryPullRequestsQuery.Node): PullRequest
}