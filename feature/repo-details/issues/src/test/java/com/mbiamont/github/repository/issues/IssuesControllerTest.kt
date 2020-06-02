package com.mbiamont.github.repository.issues

import com.mbiamont.github.domain.feature.repository.details.issues.FetchRepositoryIssuesUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class IssuesControllerTest {

    private val fetchRepositoryIssuesUseCase: FetchRepositoryIssuesUseCase = mock()
    private lateinit var controller: IssuesController

    @Before
    fun setUp() {
        controller = IssuesController(fetchRepositoryIssuesUseCase)
    }

    @Test
    fun onViewReady() = runBlockingTest {
        controller.onViewReady("foo", "bar")
        verify(fetchRepositoryIssuesUseCase).fetchRepositoryIssues("foo", "bar")
    }
}