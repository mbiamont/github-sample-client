package com.mbiamont.github.splashscreen

import com.mbiamont.github.domain.feature.splashscreen.VerifyUserSessionUseCase

class SplashController(
    private val verifyUserSessionUseCase: VerifyUserSessionUseCase
) : ISplashController {

    override suspend fun onViewReady() {
        verifyUserSessionUseCase.verifyUserSession()
    }
}