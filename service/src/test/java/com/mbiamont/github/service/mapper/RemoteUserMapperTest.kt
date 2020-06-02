package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.User
import com.mbiamont.github.service.graphql.*
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class RemoteUserMapperTest {

    private lateinit var mapper: RemoteUserMapper

    @Before
    fun setUp() {
        mapper = RemoteUserMapper()
    }

    @Test
    fun map() {
        val data = FetchUserPublicRepositoriesQuery.Owner("user", userAvatarUrl, userLogin)

        val actual = mapper.map(data)

        assertEquals(expected, actual)
    }

    @Test
    fun testMap() {
        val data = FetchRepositoryDetailsQuery.Owner("user", userAvatarUrl, userLogin)

        val actual = mapper.map(data)

        assertEquals(expected, actual)
    }

    @Test
    fun testMap1() {
        val data = FetchRepositoryIssuesQuery.Author("user", userAvatarUrl, userLogin)

        val actual = mapper.map(data)

        assertEquals(expected, actual)
    }

    @Test
    fun testMap2() {
        val data = FetchRepositoryForksQuery.Owner("user", userAvatarUrl, userLogin)

        val actual = mapper.map(data)

        assertEquals(expected, actual)
    }

    @Test
    fun testMap3() {
        val data = FetchRepositoryPullRequestsQuery.Author("user", userAvatarUrl, userLogin)

        val actual = mapper.map(data)

        assertEquals(expected, actual)
    }

    private companion object {
        const val userLogin = "foo"
        const val userAvatarUrl = "bar"

        val expected = User(userLogin, userAvatarUrl)
    }
}