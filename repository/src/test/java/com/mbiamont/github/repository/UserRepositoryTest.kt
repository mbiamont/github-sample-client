package com.mbiamont.github.repository

import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.repository.service.ICacheUserService
import com.mbiamont.github.repository.service.IConfigService
import com.mbiamont.github.repository.service.IRemoteUserService
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    private lateinit var repository: UserRepository

    private val cacheUserService: ICacheUserService = mock()
    private val remoteUserService: IRemoteUserService = mock()
    private val configService: IConfigService = mock()

    @Before
    fun setUp() {
        repository = UserRepository(cacheUserService, remoteUserService, configService)
    }

    @Test
    fun saveBearerToken() = runBlockingTest {
        repository.saveBearerToken("foo")

        verify(cacheUserService).saveBearerToken("foo")
    }

    @Test
    fun getBearerToken() = runBlockingTest {
        whenever(cacheUserService.getBearerToken()).thenReturn(success("foobar"))

        repository.getBearerToken()

        verify(cacheUserService).getBearerToken()
    }

    @Test
    fun fetchAccessToken() = runBlockingTest {
        whenever(configService.githubClientId).thenReturn("foobar")
        whenever(configService.githubSecret).thenReturn("barfoo")
        whenever(remoteUserService.fetchAccessToken("foobar", "barfoo", "bar"))
            .thenReturn(success("FOO_BAR"))

        val actual = repository.fetchAccessToken("bar")
        verify(cacheUserService).saveBearerToken("FOO_BAR")

        assertEquals(success(Unit), actual)
    }

    @Test
    fun fetchAccessToken_Failed() = runBlockingTest {
        whenever(configService.githubClientId).thenReturn("foobar")
        whenever(configService.githubSecret).thenReturn("barfoo")
        whenever(remoteUserService.fetchAccessToken("foobar", "barfoo", "bar"))
            .thenReturn(failure(RuntimeException("boom")))

        val actual = repository.fetchAccessToken("bar")
        verify(cacheUserService, never()).saveBearerToken(any())

        assertTrue(actual.isFailure)
    }
}