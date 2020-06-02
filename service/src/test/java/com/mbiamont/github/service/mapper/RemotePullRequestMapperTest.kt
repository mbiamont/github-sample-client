package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.PullRequest
import com.mbiamont.github.domain.entity.User
import com.mbiamont.github.service.graphql.FetchRepositoryPullRequestsQuery
import com.mbiamont.github.service.graphql.type.PullRequestState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class RemotePullRequestMapperTest {

    private val dateMapper: IRemoteDateMapper = mock()
    private val userMapper: IRemoteUserMapper = mock()

    private lateinit var mapper: RemotePullRequestMapper

    @Before
    fun setUp() {
        mapper = RemotePullRequestMapper(dateMapper, userMapper)
    }

    @Test
    fun map() {
        whenever(dateMapper.mapToCalendar(createdAtStr)).thenReturn(createdAt)
        whenever(userMapper.map(authorData)).thenReturn(owner)

        val actual = mapper.map(data)

        assertEquals(expected, actual)
    }

    @Test
    fun mapState() {
        assertEquals(PullRequest.State.OPEN, mapper.map(PullRequestState.OPEN))
        assertEquals(PullRequest.State.CLOSED, mapper.map(PullRequestState.CLOSED))
        assertEquals(PullRequest.State.MERGED, mapper.map(PullRequestState.MERGED))
        assertEquals(PullRequest.State.UNKNOWN, mapper.map(PullRequestState.`$UNKNOWN`))
    }

    private companion object {
        const val pullRequestId = "foobar"
        const val title = "FOO? BAR!"
        val state = PullRequest.State.MERGED
        const val createdAtStr = "2020-06-30T17:17:33Z"
        val createdAt: Calendar = Calendar.getInstance()
        val owner = User("foo", "bar")
        const val commentsCount = 406

        val expected = PullRequest(
            id = pullRequestId,
            title = title,
            state = state,
            createdAt = createdAt,
            author = owner,
            commentsCount = commentsCount
        )

        val authorData = FetchRepositoryPullRequestsQuery.Author("user", "foo", "bar")
        val comments = FetchRepositoryPullRequestsQuery.Comments("comments", commentsCount)

        val data =
            FetchRepositoryPullRequestsQuery.Node("pr", pullRequestId, authorData, comments, createdAtStr, title, PullRequestState.MERGED)
    }
}