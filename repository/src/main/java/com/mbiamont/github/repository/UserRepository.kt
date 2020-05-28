package com.mbiamont.github.repository

import com.mbiamont.github.core.map
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.repository.IUserRepository
import com.mbiamont.github.repository.service.ICacheUserService
import com.mbiamont.github.repository.service.IConfigService
import com.mbiamont.github.repository.service.IRemoteUserService

class UserRepository(
    private val cacheUserService: ICacheUserService,
    private val remoteUserService: IRemoteUserService,
    private val configService: IConfigService
) : IUserRepository {

    override suspend fun saveBearerToken(bearerToken: String) = cacheUserService.saveBearerToken(bearerToken)

    override suspend fun getBearerToken() = cacheUserService.getBearerToken()

    override suspend fun fetchAccessToken(oAuthCode: String) =
        remoteUserService.fetchAccessToken(configService.githubClientId, configService.githubSecret, oAuthCode).onSuccess {
            cacheUserService.saveBearerToken(it)
        }.map { Unit }
}