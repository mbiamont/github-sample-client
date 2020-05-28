package com.mbiamont.github.login

import com.mbiamont.github.core.android.INavigator
import com.mbiamont.github.domain.navigation.MAIN

class LoginPresenter(
    private val navigator: INavigator
) : ILoginPresenter {

    override var view: ILoginView? = null

    override fun navigateToMainView() {
        navigator.navigateTo(MAIN)
    }

    override fun displayLoginErrorView() {
        view?.displayLoginErrorMessage(R.string.errorsLogin)
    }
}