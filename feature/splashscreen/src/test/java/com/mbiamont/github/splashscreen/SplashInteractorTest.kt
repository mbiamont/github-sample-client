package com.mbiamont.github.splashscreen

import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.domain.repository.IUserRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.lang.RuntimeException

class SplashInteractorTest {

    private val userRepository: IUserRepository = mock()
    private val presenter: ISplashPresenter = mock()

    private lateinit var interactor: SplashInteractor

    @Before
    fun setUp() {
        interactor = SplashInteractor(userRepository, presenter)
    }

    @Test
    fun verifyUserSession_Logged() = runBlockingTest {
        whenever(userRepository.getBearerToken()).thenReturn(success("foobar"))

        interactor.verifyUserSession()

        verify(presenter).navigateToMainView()
        verify(presenter, never()).naviageToLoginView()
    }

    @Test
    fun verifyUserSession_NotLogged() = runBlockingTest {
        whenever(userRepository.getBearerToken()).thenReturn(failure(RuntimeException("boom")))

        interactor.verifyUserSession()

        verify(presenter, never()).navigateToMainView()
        verify(presenter).naviageToLoginView()
    }
}