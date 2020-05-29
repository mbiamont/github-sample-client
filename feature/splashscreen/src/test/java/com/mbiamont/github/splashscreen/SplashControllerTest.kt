package com.mbiamont.github.splashscreen

import com.mbiamont.github.domain.feature.splashscreen.VerifyUserSessionUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SplashControllerTest {

    private val verifyUserSessionUseCase: VerifyUserSessionUseCase = mock()
    private lateinit var controller: SplashController

    @Before
    fun setUp() {
        controller = SplashController(verifyUserSessionUseCase)
    }

    @Test
    fun onViewReady() = runBlockingTest {
        controller.onViewReady()
        verify(verifyUserSessionUseCase).verifyUserSession()
    }
}