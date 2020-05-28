package com.mbiamont.github.login

import com.mbiamont.github.domain.feature.login.FetchOAuthTokenUseCase
import com.mbiamont.github.domain.feature.login.PrepareWebViewUseCase

class LoginController(
    private val prepareWebViewUseCase: PrepareWebViewUseCase,
    private val fetchOAuthTokenUseCase: FetchOAuthTokenUseCase
) : ILoginController {

    override fun onPrepareWebView(callback: (url: String) -> Unit) = prepareWebViewUseCase.prepareWebView(callback)

    override suspend fun onPageLoaded(oAuthCode: String) = fetchOAuthTokenUseCase.fetchOAuthToken(oAuthCode)
}