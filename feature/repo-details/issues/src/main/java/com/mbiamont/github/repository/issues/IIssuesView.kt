package com.mbiamont.github.repository.issues

import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState

interface IIssuesView {

    fun displayIssueList(issuesListViewState: List<IssueViewState>)

    fun displayTimeSerieProgress(progressViewState: ProgressViewState)

    fun displayTimeSerie(timeSerieViewState: TimeSerieViewState)
}