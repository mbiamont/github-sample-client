package com.mbiamont.github.repository.pullrequests

import com.mbiamont.github.domain.feature.repository.details.pullrequests.FetchRepositoryPullRequestsUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class PullRequestControllerTest {

    private val fetchRepositoryIssuesUseCase: FetchRepositoryPullRequestsUseCase = mock()

    private lateinit var controller: PullRequestController

    @Before
    fun setUp() {
        controller = PullRequestController(fetchRepositoryIssuesUseCase)
    }

    @Test
    fun onViewReady() = runBlockingTest {
        controller.onViewReady("foo", "bar")

        verify(fetchRepositoryIssuesUseCase).fetchRepositoryPullRequests("foo", "bar")
    }
}