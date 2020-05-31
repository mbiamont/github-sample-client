package com.mbiamont.github.repository.pullrequests

import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState

interface IPullRequestsView {

    fun displayPullRequestList(pullRequestListViewState: List<PullRequestViewState>)

    fun displayTimeSerieProgress(progressViewState: ProgressViewState)

    fun displayTimeSerie(timeSerieViewState: TimeSerieViewState)
}