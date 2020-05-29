package com.mbiamont.github.login

import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.domain.repository.IConfigRepository
import com.mbiamont.github.domain.repository.IUserRepository
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginInteractorTest {

    private val configRepository: IConfigRepository = mock()
    private val userRepository: IUserRepository = mock()
    private val presenter: ILoginPresenter = mock()

    private lateinit var interactor: LoginInteractor

    @Before
    fun setUp() {
        interactor = LoginInteractor(configRepository, userRepository, presenter)
    }

    @Test
    fun prepareWebView_NoScopes() = runBlockingTest {
        whenever(configRepository.githubClientId).thenReturn("foo_bar")
        whenever(configRepository.githubOAuthScopes).thenReturn(emptyArray())

        val expected = "${LoginInteractor.GITHUB_OAUTH_AUTHORIZE_URL}?client_id=foo_bar"

        val callback: (String) -> Unit = mock()

        interactor.prepareWebView(callback)

        verify(callback).invoke(expected)
    }

    @Test
    fun prepareWebView_Scopes() = runBlockingTest {
        whenever(configRepository.githubClientId).thenReturn("foo_bar")
        whenever(configRepository.githubOAuthScopes).thenReturn(arrayOf("foo", "bar"))

        val expected = "${LoginInteractor.GITHUB_OAUTH_AUTHORIZE_URL}?client_id=foo_bar&scope=foo,bar"

        val callback: (String) -> Unit = mock()

        interactor.prepareWebView(callback)

        verify(callback).invoke(expected)
    }

    @Test
    fun fetchOAuthToken_Success() = runBlockingTest {
        whenever(userRepository.fetchAccessToken(any())).thenReturn(success(Unit))

        interactor.fetchOAuthToken("barfoo")

        verify(presenter).navigateToMainView()
    }

    @Test
    fun fetchOAuthToken_Failure() = runBlockingTest {
        whenever(userRepository.fetchAccessToken(any())).thenReturn(failure(RuntimeException("boom")))

        interactor.fetchOAuthToken("barfoo")

        verify(presenter, never()).navigateToMainView()
        verify(presenter).displayLoginErrorView()
    }
}