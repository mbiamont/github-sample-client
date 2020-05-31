package com.mbiamont.github.repository.pullrequests

interface IPullRequestsView {

    fun displayPullRequestList(pullRequestsViewState: PullRequestsViewState)
}