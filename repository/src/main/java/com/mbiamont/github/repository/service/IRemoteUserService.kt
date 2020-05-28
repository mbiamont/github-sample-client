package com.mbiamont.github.repository.service

import com.mbiamont.github.core.Monad

interface IRemoteUserService {

    suspend fun fetchAccessToken(clientId: String, clientSecret: String, oAuthCode: String): Monad<String>
}