package com.mbiamont.github.domain.datasource

import com.mbiamont.github.core.Monad

interface IUserDataSource {

    suspend fun saveBearerToken(bearerToken: String)

    suspend fun getBearerToken(): Monad<String>

    suspend fun fetchAccessToken(oAuthCode: String): Monad<Unit>
}