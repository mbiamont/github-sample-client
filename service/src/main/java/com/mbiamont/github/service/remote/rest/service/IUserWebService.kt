package com.mbiamont.github.service.remote.rest.service

import com.mbiamont.github.service.remote.rest.model.AccessTokenResponse
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface IUserWebService {

    @Headers("Accept: application/json")
    @POST("/login/oauth/access_token")
    suspend fun fetchAccessToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") oAuthCode: String,
    ): Response<AccessTokenResponse>
}