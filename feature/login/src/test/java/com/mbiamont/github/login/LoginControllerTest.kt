package com.mbiamont.github.login

import com.mbiamont.github.domain.feature.login.FetchOAuthTokenUseCase
import com.mbiamont.github.domain.feature.login.PrepareWebViewUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginControllerTest {

    private val prepareWebViewUseCase: PrepareWebViewUseCase = mock()
    private val fetchOAuthTokenUseCase: FetchOAuthTokenUseCase = mock()

    private lateinit var controller: LoginController

    @Before
    fun setUp() {
        controller = LoginController(prepareWebViewUseCase, fetchOAuthTokenUseCase)
    }

    @Test
    fun onPrepareWebView() = runBlockingTest {
        val callback: (String) -> Unit = mock()
        controller.onPrepareWebView(callback)

        verify(prepareWebViewUseCase).prepareWebView(callback)
    }

    @Test
    fun onPageLoaded() = runBlockingTest {
        controller.onPageLoaded("foobar")

        verify(fetchOAuthTokenUseCase).fetchOAuthToken("foobar")
    }
}