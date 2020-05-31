package com.mbiamont.github.repository.pullrequests

import com.mbiamont.github.domain.entity.PullRequest

class PullRequestsPresenter(
    private val viewStateMapper: IPullRequestsViewStateMapper
) : IPullRequestsPresenter {

    override var view: IPullRequestsView? = null

    override fun displayPullRequests(pullRequestsList: List<PullRequest>, count: Int, totalCount: Int, pullRequestsPerWeek: Array<Int>?) {
        view?.displayPullRequestList(
            PullRequestsViewState(
                pullRequestsList.map { viewStateMapper.map(it) },
                viewStateMapper.map(count, totalCount, pullRequestsPerWeek)
            )
        )
    }

}