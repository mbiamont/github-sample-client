package com.mbiamont.github.repository.issues

import com.mbiamont.github.domain.entity.Issue

interface IIssuesViewStateMapper {

    fun map(count: Int, totalCount: Int, issuesPerWeek: Array<Int>?): IssueTimeSerieViewState

    fun map(issue: Issue): IssueViewState
}