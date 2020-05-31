package com.mbiamont.github.repository.pullrequests

import com.mbiamont.github.core.BasePresenter
import com.mbiamont.github.domain.entity.PullRequest

interface IPullRequestsPresenter: BasePresenter<IPullRequestsView> {

    fun displayPullRequests(pullRequestsList: List<PullRequest>, count: Int, totalCount: Int, pullRequestsPerWeek: Array<Int>?)
}