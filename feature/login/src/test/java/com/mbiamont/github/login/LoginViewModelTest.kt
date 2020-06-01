package com.mbiamont.github.login

import android.net.Uri
import com.mbiamont.github.core.test.coroutine.MockCoroutineContextProvider
import com.mbiamont.github.core.test.rule.AsyncLiveDataRule
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule

class LoginViewModelTest {

    @get:Rule
    var rule: TestRule = AsyncLiveDataRule()

    private val controller: ILoginController = mock()
    private val presenter: ILoginPresenter = mock()

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        viewModel = LoginViewModel(MockCoroutineContextProvider, controller, presenter)

        verify(presenter).onAttachView(viewModel)
    }

    @Test
    fun onPrepareWebView() {
        val callback: (String) -> Unit = mock()

        viewModel.onPrepareWebView(callback)

        verify(controller).onPrepareWebView(callback)
    }

    @Test
    fun onPageLoaded() = runBlockingTest {
        val uri: Uri = mock()

        whenever(uri.getQueryParameter("code")).thenReturn("foobar")

        val actual = viewModel.onPageLoaded(uri)

        verify(controller).onPageLoaded("foobar")
        assertTrue(actual)
    }

    @Test
    fun onPageLoaded_NoCode() = runBlockingTest {
        val uri: Uri = mock()

        whenever(uri.getQueryParameter("code")).thenReturn(null)

        val actual = viewModel.onPageLoaded(uri)

        verify(controller, never()).onPageLoaded(any())
        assertFalse(actual)
    }

    @Test
    fun onPageLoaded_InvalidCode() = runBlockingTest {
        val uri: Uri = mock()

        whenever(uri.getQueryParameter("code")).thenReturn("")

        val actual = viewModel.onPageLoaded(uri)

        verify(controller, never()).onPageLoaded(any())
        assertFalse(actual)
    }

    @Test
    fun displayLoginErrorMessage() {
        viewModel.displayErrorMessage(32)

        assertEquals(32, viewModel.errorMessageLiveData.value)
    }
}