package com.mbiamont.github.splashscreen

import com.mbiamont.github.core.test.coroutine.MockCoroutineContextProvider
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SplashViewModelTest {

    private val controller: ISplashController = mock()

    private lateinit var viewModel: SplashViewModel

    @Before
    fun setUp() {
        viewModel = SplashViewModel(MockCoroutineContextProvider, controller)
    }

    @Test
    fun onViewReady() = runBlockingTest {
        viewModel.onViewReady().join()

        verify(controller).onViewReady()
    }
}