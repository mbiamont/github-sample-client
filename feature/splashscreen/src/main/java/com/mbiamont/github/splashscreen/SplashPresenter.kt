package com.mbiamont.github.splashscreen

import com.mbiamont.github.core.android.INavigator
import com.mbiamont.github.domain.navigation.LOGIN
import com.mbiamont.github.domain.navigation.REPOS_LIST

class SplashPresenter(
    private val navigator: INavigator
) : ISplashPresenter {

    override fun navigateToMainView() {
        navigator.navigateTo(REPOS_LIST)
    }

    override fun naviageToLoginView() {
        navigator.navigateTo(LOGIN)
    }
}