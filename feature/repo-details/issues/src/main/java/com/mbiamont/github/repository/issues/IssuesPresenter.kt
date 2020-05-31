package com.mbiamont.github.repository.issues

import com.mbiamont.github.domain.entity.Issue

class IssuesPresenter(
    private val viewStateMapper: IIssuesViewStateMapper
) : IIssuesPresenter {

    override var view: IIssuesView? = null

    override fun displayIssues(issuesList: List<Issue>, count: Int, totalCount: Int, issuesPerWeek: Array<Int>?) {
        view?.displayIssueList(
            IssuesViewState(
                issuesList.map { viewStateMapper.map(it) },
                viewStateMapper.map(count, totalCount, issuesPerWeek)
            )
        )
    }

}