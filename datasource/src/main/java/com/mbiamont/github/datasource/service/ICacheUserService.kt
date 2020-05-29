package com.mbiamont.github.datasource.service

import com.mbiamont.github.core.Monad

interface ICacheUserService {

    suspend fun saveBearerToken(bearerToken: String)

    suspend fun getBearerToken(): Monad<String>
}