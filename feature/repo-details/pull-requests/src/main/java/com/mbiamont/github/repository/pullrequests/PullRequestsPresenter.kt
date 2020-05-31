package com.mbiamont.github.repository.pullrequests

import com.mbiamont.github.domain.entity.PullRequest

class PullRequestsPresenter(
    private val viewStateMapper: IPullRequestsViewStateMapper
) : IPullRequestsPresenter {

    override var view: IPullRequestsView? = null

    override fun displayPullRequests(pullRequestsList: List<PullRequest>) {
        view?.displayPullRequestList(pullRequestsList.map { viewStateMapper.map(it) })
    }

    override fun displayTimeSerieProgress(progress: Int, totalCount: Int) {
        view?.displayTimeSerieProgress(viewStateMapper.map(progress, totalCount))
    }

    override fun displayTimeSerie(pullRequestsPerWeek: Array<Int>) {
        view?.displayTimeSerie(viewStateMapper.map(pullRequestsPerWeek))
    }

}