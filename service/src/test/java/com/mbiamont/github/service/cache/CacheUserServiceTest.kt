package com.mbiamont.github.service.cache

import com.mbiamont.github.core.Monad
import com.mbiamont.github.core.success
import com.mbiamont.github.domain.exception.CacheException
import com.mbiamont.github.service.prefs.IPreference
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

@ExperimentalCoroutinesApi
class CacheUserServiceTest {

    private val bearerTokenPref: IPreference<String> = mock()

    private lateinit var service: CacheUserService

    @Before
    fun setUp() {
        service = CacheUserService(bearerTokenPref)
    }

    @Test
    fun saveBearerToken() = runBlockingTest {
        service.saveBearerToken("foobar")
        verify(bearerTokenPref).set("foobar")
    }

    @Test
    fun getBearerToken_Valid() = runBlockingTest {
        whenever(bearerTokenPref.get()).thenReturn("foobar")

        val expected = success("foobar")
        val actual = service.getBearerToken()

        assertEquals(expected, actual)
    }

    @Test
    fun getBearerToken_Empty() = runBlockingTest {
        whenever(bearerTokenPref.get()).thenReturn("")

        val actual = service.getBearerToken()

        assertTrue(actual is Monad.Failure)
        assertTrue((actual as Monad.Failure).error is CacheException)
    }

    @Test
    fun getBearerToken_Null() = runBlockingTest {
        val actual = service.getBearerToken()

        assertTrue(actual is Monad.Failure)
        assertTrue((actual as Monad.Failure).error is CacheException)
    }
}