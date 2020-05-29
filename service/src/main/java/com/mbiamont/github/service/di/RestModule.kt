package com.mbiamont.github.service.di

import com.mbiamont.github.core.qualifier.githubRestBaseUrl
import com.mbiamont.github.core.qualifier.restQualifier
import com.mbiamont.github.service.remote.rest.service.IUserWebService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val restModule = module {

    single { createWebService<IUserWebService>(get()) }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(githubRestBaseUrl))
            .client(get(restQualifier))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single(restQualifier) {
        OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
}

private inline fun <reified T> createWebService(retrofit: Retrofit): T = retrofit.create(T::class.java)