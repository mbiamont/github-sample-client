package com.mbiamont.github.domain.feature.login

interface FetchOAuthTokenUseCase {

    suspend fun fetchOAuthToken(code: String)
}