package com.mbiamont.github.service.cache

import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.domain.exception.CacheException
import com.mbiamont.github.repository.service.ICacheUserService
import com.mbiamont.github.service.prefs.StringPref

class CacheUserService(
    private val bearerTokenPref: StringPref,
) : ICacheUserService {

    override suspend fun saveBearerToken(bearerToken: String) = bearerTokenPref.set(bearerToken)

    override suspend fun getBearerToken() = bearerTokenPref
        .get()
        .takeIf { it?.isNotEmpty() ?: false } // we only want it if it's not empty
        ?.let { success(it) }
        ?: failure(CacheException("No bearer token in preference"))
}