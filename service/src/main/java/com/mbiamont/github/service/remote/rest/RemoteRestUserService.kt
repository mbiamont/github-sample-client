package com.mbiamont.github.service.remote.rest

import com.mbiamont.github.core.Monad
import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.domain.exception.NetworkException
import com.mbiamont.github.repository.service.IRemoteUserService
import com.mbiamont.github.service.extensions.networkException
import com.mbiamont.github.service.remote.rest.model.AccessTokenResponse
import com.mbiamont.github.service.remote.rest.service.IUserWebService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class RemoteRestUserService(
    private val userWebService: IUserWebService
) : IRemoteUserService {

    override suspend fun fetchAccessToken(clientId: String, clientSecret: String, oAuthCode: String): Monad<String> {
        lateinit var response: Response<AccessTokenResponse>
        try {
            response = userWebService.fetchAccessToken(clientId, clientSecret, oAuthCode)
        } catch (e: IOException) {
            return failure(NetworkException(e.message))
        }

        response.body()?.let {
            Timber.i("[MELVIN] TOKEN_TYPE = ${it.tokenType}")
            Timber.i("[MELVIN] ACCESS_TOKEN = ${it.accessToken}")
            return success(it.accessToken)
        }
        return failure(response.networkException())
    }
}