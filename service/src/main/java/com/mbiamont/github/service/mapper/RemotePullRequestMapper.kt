package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.PullRequest
import com.mbiamont.github.service.graphql.FetchRepositoryPullRequestsQuery
import com.mbiamont.github.service.graphql.type.PullRequestState
import java.util.*

class RemotePullRequestMapper(
    private val dateMapper: IRemoteDateMapper,
    private val userMapper: IRemoteUserMapper
) : IRemotePullRequestMapper {

    override fun map(pullRequest: FetchRepositoryPullRequestsQuery.Node) = PullRequest(
        id = pullRequest.id(),
        title = pullRequest.title(),
        state = PullRequest.State.CLOSED,
        createdAt = dateMapper.mapToCalendar(pullRequest.createdAt() as? String ?: "") ?: Calendar.getInstance(),
        author = userMapper.map(pullRequest.author()),
        commentsCount = pullRequest.comments().totalCount()
    )

    private fun map(state: PullRequestState) = when (state) {
        PullRequestState.OPEN -> PullRequest.State.OPEN
        PullRequestState.CLOSED -> PullRequest.State.CLOSED
        PullRequestState.MERGED -> PullRequest.State.MERGED
        PullRequestState.`$UNKNOWN` -> PullRequest.State.UNKNOWN
    }
}