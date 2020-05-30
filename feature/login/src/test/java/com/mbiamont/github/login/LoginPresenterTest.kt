package com.mbiamont.github.login

import com.mbiamont.github.core.android.INavigator
import com.mbiamont.github.domain.navigation.REPOS_LIST
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

class LoginPresenterTest {

    private val navigator: INavigator = mock()
    private val view: ILoginView = mock()

    private lateinit var presenter: LoginPresenter

    @Before
    fun setUp() {
        presenter = LoginPresenter(navigator)
        presenter.onAttachView(view)
    }

    @Test
    fun navigateToMainView() {
        presenter.navigateToMainView()
        verify(navigator).navigateTo(REPOS_LIST)
    }

    @Test
    fun displayLoginErrorView() {
        presenter.displayLoginErrorView()
        verify(view).displayLoginErrorMessage(R.string.errorsLogin)
    }

    @Test
    fun displayLoginErrorView_Detached() {
        presenter.onDetachView()
        presenter.displayLoginErrorView()
        verify(view, never()).displayLoginErrorMessage(any())
    }
}