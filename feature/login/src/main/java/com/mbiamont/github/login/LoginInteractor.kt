package com.mbiamont.github.login

import com.mbiamont.github.core.onFailure
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.feature.login.FetchOAuthTokenUseCase
import com.mbiamont.github.domain.feature.login.PrepareWebViewUseCase
import com.mbiamont.github.domain.repository.IConfigRepository
import com.mbiamont.github.domain.repository.IUserRepository
import timber.log.Timber

class LoginInteractor(
    private val configRepository: IConfigRepository,
    private val userRepository: IUserRepository,
    private val presenter: ILoginPresenter
) : PrepareWebViewUseCase, FetchOAuthTokenUseCase {

    override fun prepareWebView(callback: (url: String) -> Unit) {
        val scopes = configRepository.githubOAuthScopes

        var url = "$GITHUB_OAUTH_AUTHORIZE_URL?client_id=${configRepository.githubClientId}"
        if (scopes.isNotEmpty()) {
            url += "&scope=${scopes.joinToString(",")}"
        }

        callback(url)
    }

    override suspend fun fetchOAuthToken(code: String) {
        Timber.i("[MELVIN] CODE: $code")

        userRepository.fetchAccessToken(code).onSuccess {
            presenter.navigateToMainView()
        }.onFailure {
            Timber.e(it)
            presenter.displayLoginErrorView()
        }
    }

    companion object {
        const val GITHUB_OAUTH_AUTHORIZE_URL = "https://github.com/login/oauth/authorize"
    }
}