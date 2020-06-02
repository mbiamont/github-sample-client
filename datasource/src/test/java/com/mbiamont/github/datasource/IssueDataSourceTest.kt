package com.mbiamont.github.datasource

import com.mbiamont.github.datasource.service.IRemoteIssueService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class IssueDataSourceTest {

    private val remoteIssueService: IRemoteIssueService = mock()

    private lateinit var issueDataSource: IssueDataSource

    @Before
    fun setUp() {
        issueDataSource = IssueDataSource(remoteIssueService)
    }

    @Test
    fun getRepositoryIssues() = runBlockingTest{
        issueDataSource.getRepositoryIssues("foo", "bar", "baz")
        verify(remoteIssueService).getRepositoryIssues("foo", "bar", "baz")
    }
}