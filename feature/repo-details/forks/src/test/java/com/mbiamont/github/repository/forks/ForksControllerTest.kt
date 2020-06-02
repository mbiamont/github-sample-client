package com.mbiamont.github.repository.forks

import com.mbiamont.github.domain.feature.repository.details.forks.FetchRepositoryForksUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

@ExperimentalCoroutinesApi
class ForksControllerTest {
    private val fetchRepositoryIssuesUseCase: FetchRepositoryForksUseCase = mock()

    private lateinit var controller: ForksController

    @Before
    fun setUp() {
        controller = ForksController(fetchRepositoryIssuesUseCase)
    }

    @Test
    fun onViewReady() = runBlockingTest {
        controller.onViewReady("foo", "bar")
        verify(fetchRepositoryIssuesUseCase).fetchRepositoryForks("foo", "bar")
    }
}