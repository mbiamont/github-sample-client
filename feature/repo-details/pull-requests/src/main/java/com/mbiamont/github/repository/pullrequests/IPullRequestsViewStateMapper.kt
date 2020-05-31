package com.mbiamont.github.repository.pullrequests

import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState
import com.mbiamont.github.domain.entity.PullRequest

interface IPullRequestsViewStateMapper {

    fun map(progress: Int, totalCount: Int): ProgressViewState

    fun map(pullRequestsPerWeek: Array<Int>): TimeSerieViewState

    fun map(pullRequest: PullRequest): PullRequestViewState
}