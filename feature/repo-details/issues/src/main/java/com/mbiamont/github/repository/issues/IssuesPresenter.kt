package com.mbiamont.github.repository.issues

import com.mbiamont.github.domain.entity.Issue

class IssuesPresenter(
    private val viewStateMapper: IIssuesViewStateMapper
) : IIssuesPresenter {

    override var view: IIssuesView? = null

    override fun displayIssues(issuesList: List<Issue>) {
        view?.displayIssueList(issuesList.map { viewStateMapper.map(it) })
    }

    override fun displayTimeSerieProgress(isLoading: Boolean) {
        view?.displayTimeSerieProgress(viewStateMapper.map(isLoading))
    }

    override fun displayTimeSerie(issuesPerWeek: Array<Int>) {
        view?.displayTimeSerie(viewStateMapper.map(issuesPerWeek))
    }

    override fun displayFetchIssuesError() {
        view?.displayErrorMessage(R.string.errorsFetchingIssues)
    }

}