package com.mbiamont.github.splashscreen

import com.mbiamont.github.core.onFailure
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.datasource.IUserDataSource
import com.mbiamont.github.domain.feature.splashscreen.VerifyUserSessionUseCase
import timber.log.Timber

class SplashInteractor(
    private val userDataSource: IUserDataSource,
    private val presenter: ISplashPresenter
) : VerifyUserSessionUseCase {

    override suspend fun verifyUserSession() {
        userDataSource.getBearerToken().onSuccess {
            Timber.i("[MELVIN] BEARER TOKEN: $it")
            presenter.navigateToMainView()
        }.onFailure {
            presenter.naviageToLoginView()
        }
    }
}