package com.mbiamont.github.datasource

import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.datasource.service.ICacheUserService
import com.mbiamont.github.datasource.service.IConfigService
import com.mbiamont.github.datasource.service.IRemoteUserService
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class UserDataSourceTest {

    private lateinit var dataSource: UserDataSource

    private val cacheUserService: ICacheUserService = mock()
    private val remoteUserService: IRemoteUserService = mock()
    private val configService: IConfigService = mock()

    @Before
    fun setUp() {
        dataSource = UserDataSource(cacheUserService, remoteUserService, configService)
    }

    @Test
    fun saveBearerToken() = runBlockingTest {
        dataSource.saveBearerToken("foo")

        verify(cacheUserService).saveBearerToken("foo")
    }

    @Test
    fun getBearerToken() = runBlockingTest {
        whenever(cacheUserService.getBearerToken()).thenReturn(success("foobar"))

        dataSource.getBearerToken()

        verify(cacheUserService).getBearerToken()
    }

    @Test
    fun fetchAccessToken() = runBlockingTest {
        whenever(configService.githubClientId).thenReturn("foobar")
        whenever(configService.githubSecret).thenReturn("barfoo")
        whenever(remoteUserService.fetchAccessToken("foobar", "barfoo", "bar"))
            .thenReturn(success("FOO_BAR"))

        val actual = dataSource.fetchAccessToken("bar")
        verify(cacheUserService).saveBearerToken("FOO_BAR")

        assertEquals(success(Unit), actual)
    }

    @Test
    fun fetchAccessToken_Failed() = runBlockingTest {
        whenever(configService.githubClientId).thenReturn("foobar")
        whenever(configService.githubSecret).thenReturn("barfoo")
        whenever(remoteUserService.fetchAccessToken("foobar", "barfoo", "bar"))
            .thenReturn(failure(RuntimeException("boom")))

        val actual = dataSource.fetchAccessToken("bar")
        verify(cacheUserService, never()).saveBearerToken(any())

        assertTrue(actual.isFailure)
    }
}