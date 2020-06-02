package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.domain.entity.User
import com.mbiamont.github.service.graphql.FetchRepositoryIssuesQuery
import com.mbiamont.github.service.graphql.type.IssueState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class RemoteIssueMapperTest {

    private val dateMapper: IRemoteDateMapper = mock()
    private val userMapper: IRemoteUserMapper = mock()

    private lateinit var mapper: RemoteIssueMapper

    @Before
    fun setUp() {
        mapper = RemoteIssueMapper(dateMapper, userMapper)
    }

    @Test
    fun map() {
        whenever(dateMapper.mapToCalendar(createdAtStr)).thenReturn(createdAt)
        whenever(userMapper.map(dataAuthor)).thenReturn(owner)

        val actual = mapper.map(data)

        assertEquals(expected, actual)
    }

    @Test
    fun mapState() {
        assertEquals(Issue.State.OPEN, mapper.map(IssueState.OPEN))
        assertEquals(Issue.State.CLOSED, mapper.map(IssueState.CLOSED))
        assertEquals(Issue.State.UNKNOWN, mapper.map(IssueState.`$UNKNOWN`))
    }

    private companion object {
        const val issueId = "foobar"
        const val issueTitle = "BAR FOO!"
        val state = Issue.State.OPEN
        const val createdAtStr = "2020-06-30T17:17:33Z"
        val createdAt: Calendar = Calendar.getInstance()
        val owner = User("foo", "bar")
        const val commentsCount = 626

        val expected = Issue(
            id = issueId,
            title = issueTitle,
            state = state,
            createdAt = createdAt,
            author = owner,
            commentsCount = commentsCount,
        )

        val dataAuthor = FetchRepositoryIssuesQuery.Author("user", "foo", "bar")
        val dataComments = FetchRepositoryIssuesQuery.Comments("comments", commentsCount)
        val data = FetchRepositoryIssuesQuery.Node("issue", issueId, IssueState.OPEN, issueTitle, createdAtStr, dataAuthor, dataComments)
    }
}