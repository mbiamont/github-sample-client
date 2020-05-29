package com.mbiamont.github.login

import com.mbiamont.github.core.onFailure
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.feature.login.FetchOAuthTokenUseCase
import com.mbiamont.github.domain.feature.login.PrepareWebViewUseCase
import com.mbiamont.github.domain.datasource.IConfigDataSource
import com.mbiamont.github.domain.datasource.IUserDataSource
import timber.log.Timber

class LoginInteractor(
    private val configDataSource: IConfigDataSource,
    private val userDataSource: IUserDataSource,
    private val presenter: ILoginPresenter
) : PrepareWebViewUseCase, FetchOAuthTokenUseCase {

    override fun prepareWebView(callback: (url: String) -> Unit) {
        val scopes = configDataSource.githubOAuthScopes

        var url = "$GITHUB_OAUTH_AUTHORIZE_URL?client_id=${configDataSource.githubClientId}"
        if (scopes.isNotEmpty()) {
            url += "&scope=${scopes.joinToString(",")}"
        }

        callback(url)
    }

    override suspend fun fetchOAuthToken(code: String) {
        Timber.i("[MELVIN] CODE: $code")

        userDataSource.fetchAccessToken(code).onSuccess {
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