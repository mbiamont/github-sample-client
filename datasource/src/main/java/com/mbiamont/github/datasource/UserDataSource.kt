package com.mbiamont.github.datasource

import com.mbiamont.github.core.map
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.datasource.IUserDataSource
import com.mbiamont.github.datasource.service.ICacheUserService
import com.mbiamont.github.datasource.service.IConfigService
import com.mbiamont.github.datasource.service.IRemoteUserService

class UserDataSource(
    private val cacheUserService: ICacheUserService,
    private val remoteUserService: IRemoteUserService,
    private val configService: IConfigService
) : IUserDataSource {

    override suspend fun saveBearerToken(bearerToken: String) = cacheUserService.saveBearerToken(bearerToken)

    override suspend fun getBearerToken() = cacheUserService.getBearerToken()

    override suspend fun fetchAccessToken(oAuthCode: String) =
        remoteUserService.fetchAccessToken(configService.githubClientId, configService.githubSecret, oAuthCode).onSuccess {
            cacheUserService.saveBearerToken(it)
        }.map { Unit }
}