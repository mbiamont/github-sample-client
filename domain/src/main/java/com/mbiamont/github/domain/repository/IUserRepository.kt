package com.mbiamont.github.domain.repository

import com.mbiamont.github.core.Monad

interface IUserRepository {

    suspend fun saveBearerToken(bearerToken: String)

    suspend fun getBearerToken(): Monad<String>

    suspend fun fetchAccessToken(oAuthCode: String): Monad<Unit>
}