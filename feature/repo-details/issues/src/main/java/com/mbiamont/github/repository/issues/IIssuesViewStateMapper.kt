package com.mbiamont.github.repository.issues

import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState
import com.mbiamont.github.domain.entity.Issue

interface IIssuesViewStateMapper {

    fun map(isLoading: Boolean): ProgressViewState

    fun map(issuesPerWeek: Array<Int>): TimeSerieViewState

    fun map(issue: Issue): IssueViewState
}