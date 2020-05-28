package com.mbiamont.github.login

import com.mbiamont.github.core.BasePresenter

interface ILoginPresenter: BasePresenter<ILoginView> {

    fun navigateToMainView()

    fun displayLoginErrorView()
}