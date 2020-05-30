package com.mbiamont.github.repository.issues

import com.mbiamont.github.domain.entity.Issue

class IssuesPresenter : IIssuesPresenter {

    override var view: IIssuesView? = null

    override fun displayIssues(issuesList: List<Issue>) {
        //TODO
    }

}