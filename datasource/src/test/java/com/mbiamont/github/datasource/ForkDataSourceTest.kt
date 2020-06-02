package com.mbiamont.github.datasource

import com.mbiamont.github.datasource.service.IRemoteForkService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

@ExperimentalCoroutinesApi
class ForkDataSourceTest {

    private val remoteForkService: IRemoteForkService = mock()

    private lateinit var dataSource: ForkDataSource

    @Before
    fun setUp() {
        dataSource = ForkDataSource(remoteForkService)
    }

    @Test
    fun getRepositoryForks() = runBlockingTest {
        dataSource.getRepositoryForks("foo", "bar", "baz")
        verify(remoteForkService).getRepositoryForks("foo", "bar", "baz")
    }
}