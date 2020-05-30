package com.mbiamont.github.splashscreen

import com.mbiamont.github.core.android.INavigator
import com.mbiamont.github.domain.navigation.LOGIN
import com.mbiamont.github.domain.navigation.REPOS_LIST
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

class SplashPresenterTest {

    private val navigator: INavigator = mock()
    private lateinit var presenter: SplashPresenter

    @Before
    fun setUp() {
        presenter = SplashPresenter(navigator)
    }

    @Test
    fun navigateToMainView() {
        presenter.navigateToMainView()
        verify(navigator).navigateTo(REPOS_LIST)
    }

    @Test
    fun naviageToLoginView() {
        presenter.naviageToLoginView()
        verify(navigator).navigateTo(LOGIN)
    }
}