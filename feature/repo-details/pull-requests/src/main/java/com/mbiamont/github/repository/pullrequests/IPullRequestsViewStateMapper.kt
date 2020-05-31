package com.mbiamont.github.repository.pullrequests

import com.mbiamont.github.domain.entity.PullRequest

interface IPullRequestsViewStateMapper {

    fun map(count: Int, totalCount: Int, pullRequestsPerWeek: Array<Int>?): PullRequestTimeSerieViewState

    fun map(pullRequest: PullRequest): PullRequestViewState
}