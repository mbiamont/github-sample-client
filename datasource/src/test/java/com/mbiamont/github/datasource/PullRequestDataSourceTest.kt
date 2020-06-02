package com.mbiamont.github.datasource

import com.mbiamont.github.datasource.service.IRemotePullRequestService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

@ExperimentalCoroutinesApi
class PullRequestDataSourceTest {

    private val remotePullRequestService: IRemotePullRequestService = mock()

    private lateinit var dataSource: PullRequestDataSource

    @Before
    fun setUp() {
        dataSource = PullRequestDataSource(remotePullRequestService)
    }

    @Test
    fun getRepositoryPullRequests() = runBlockingTest{
        dataSource.getRepositoryPullRequests("foo", "bar", "baz")
        verify(remotePullRequestService).getRepositoryPullRequests("foo", "bar", "baz")
    }
}