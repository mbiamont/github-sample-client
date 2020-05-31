package com.mbiamont.github.repository.issues

import com.mbiamont.github.domain.entity.Issue

class IssuesPresenter(
    private val viewStateMapper: IIssuesViewStateMapper
) : IIssuesPresenter {

    override var view: IIssuesView? = null

    override fun displayIssues(issuesList: List<Issue>) {
        view?.displayIssueList(issuesList.map { viewStateMapper.map(it) })
    }

    override fun displayTimeSerieProgress(progress: Int, totalCount: Int) {
        view?.displayTimeSerieProgress(viewStateMapper.map(progress, totalCount))
    }

    override fun displayTimeSerie(issuesPerWeek: Array<Int>) {
        view?.displayTimeSerie(viewStateMapper.map(issuesPerWeek))
    }

}