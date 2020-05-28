package com.mbiamont.github.service.di

import com.mbiamont.github.repository.service.IRemoteUserService
import com.mbiamont.github.service.remote.rest.RemoteRestUserService
import com.mbiamont.github.service.remote.rest.service.IUserWebService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val restModule = module {

    single<IRemoteUserService> { RemoteRestUserService(get()) }

    single { createWebService<IUserWebService>(get()) }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(GITHUB_BASE_URL)))
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
}

private inline fun <reified T> createWebService(retrofit: Retrofit): T = retrofit.create(T::class.java)

const val GITHUB_BASE_URL = "GITHUB_BASE_URL"