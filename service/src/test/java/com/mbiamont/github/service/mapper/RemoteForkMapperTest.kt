package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.Fork
import com.mbiamont.github.domain.entity.User
import com.mbiamont.github.service.graphql.FetchRepositoryForksQuery
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class RemoteForkMapperTest {

    private val dateMapper: IRemoteDateMapper = mock()
    private val userMapper: IRemoteUserMapper = mock()

    private lateinit var mapper: RemoteForkMapper

    @Before
    fun setUp() {
        mapper = RemoteForkMapper(dateMapper, userMapper)
    }

    @Test
    fun map() {
        whenever(dateMapper.mapToCalendar(createdAtStr)).thenReturn(createdAt)
        whenever(userMapper.map(dataOwner)).thenReturn(owner)

        val actual = mapper.map(dataFork)

        assertEquals(expected, actual)
    }

    private companion object {
        const val forkId = "foo_bar"
        const val createdAtStr = "2020-06-30T17:17:33Z"
        val createdAt: Calendar = Calendar.getInstance()
        val owner = User("foo", "bar")

        val expected = Fork(
            id = forkId,
            createdAt = createdAt,
            owner = owner
        )

        val dataOwner = FetchRepositoryForksQuery.Owner("user", "foo", "bar")
        val dataFork = FetchRepositoryForksQuery.Node("fork", forkId, createdAtStr, dataOwner)
    }
}