package com.mbiamont.github.datasource

import com.mbiamont.github.datasource.service.IRemoteRepositoryService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

@ExperimentalCoroutinesApi
class RepositoryDataSourceTest {

    private val remoteRepositoryService: IRemoteRepositoryService = mock()

    private lateinit var dataSource: RepositoryDataSource

    @Before
    fun setUp() {
        dataSource = RepositoryDataSource(remoteRepositoryService)
    }

    @Test
    fun getUserPublicRepositories() = runBlockingTest {
        dataSource.getUserPublicRepositories("foobar")

        verify(remoteRepositoryService).getUserPublicRepositories("foobar")
    }

    @Test
    fun getRepositoryWithNameAndOwner() = runBlockingTest {
        dataSource.getRepositoryWithNameAndOwner("foo", "bar")

        verify(remoteRepositoryService).getRepositoryWithNameAndOwner("foo", "bar")
    }
}