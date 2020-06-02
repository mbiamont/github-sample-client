package com.mbiamont.github.service.mapper

import androidx.annotation.VisibleForTesting
import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.service.graphql.FetchRepositoryIssuesQuery
import com.mbiamont.github.service.graphql.type.IssueState
import java.util.*

class RemoteIssueMapper(
    private val dateMapper: IRemoteDateMapper,
    private val userMapper: IRemoteUserMapper
) : IRemoteIssueMapper {

    override fun map(issue: FetchRepositoryIssuesQuery.Node) = Issue(
        id = issue.id(),
        title = issue.title(),
        state = map(issue.state()),
        createdAt = dateMapper.mapToCalendar(issue.createdAt() as? String ?: "") ?: Calendar.getInstance(),
        author = userMapper.map(issue.author()),
        commentsCount = issue.comments().totalCount(),
    )

    @VisibleForTesting
    fun map(state: IssueState) = when (state) {
        IssueState.OPEN -> Issue.State.OPEN
        IssueState.CLOSED -> Issue.State.CLOSED
        else -> Issue.State.UNKNOWN
    }
}