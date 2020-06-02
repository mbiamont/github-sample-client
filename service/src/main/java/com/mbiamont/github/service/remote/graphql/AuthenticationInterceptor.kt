package com.mbiamont.github.service.remote.graphql

import com.mbiamont.github.service.prefs.IPreference
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(
    private val bearerTokenPref: IPreference<String>
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val bearerToken = bearerTokenPref.get() ?: ""

        val newRequestBuilder = original.newBuilder()
        newRequestBuilder.header("Authorization", "Bearer $bearerToken")

        return chain.proceed(newRequestBuilder.build())
    }
}