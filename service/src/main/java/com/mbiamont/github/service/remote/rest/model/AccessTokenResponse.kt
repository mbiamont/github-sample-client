package com.mbiamont.github.service.remote.rest.model

import com.squareup.moshi.Json

data class AccessTokenResponse(
    @field:Json(name = "access_token") val accessToken: String,
    @field:Json(name = "token_type") val tokenType: String
)