package com.mbiamont.github.repository.details

import com.mbiamont.github.domain.feature.repository.details.FetchRepositoryDetailsUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class RepositoryDetailsControllerTest {

    private val fetchRepositoryDetailsUseCase: FetchRepositoryDetailsUseCase = mock()

    private lateinit var controller: RepositoryDetailsController

    @Before
    fun setUp() {
        controller = RepositoryDetailsController(fetchRepositoryDetailsUseCase)
    }

    @Test
    fun onViewReady() = runBlockingTest {
        controller.onViewReady("foo", "bar")
        verify(fetchRepositoryDetailsUseCase).fetchRepositoryDetails("foo", "bar")
    }
}