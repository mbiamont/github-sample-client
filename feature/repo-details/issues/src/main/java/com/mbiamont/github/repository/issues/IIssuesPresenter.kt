package com.mbiamont.github.repository.issues

import com.mbiamont.github.core.BasePresenter
import com.mbiamont.github.domain.entity.Issue

interface IIssuesPresenter : BasePresenter<IIssuesView> {

    fun displayIssues(issuesList: List<Issue>)

    fun displayTimeSerieProgress(progress: Int, totalCount: Int)

    fun displayTimeSerie(issuesPerWeek: Array<Int>)
}