package com.mbiamont.github.service.di

import com.apollographql.apollo.ApolloClient
import com.mbiamont.github.core.qualifier.authenticationInterceptorQualifier
import com.mbiamont.github.core.qualifier.bearerTokenQualifier
import com.mbiamont.github.core.qualifier.githubGraphQlUrl
import com.mbiamont.github.core.qualifier.graphQlQualifier
import com.mbiamont.github.service.FetchRepositoryWithNameAndOwnerQuery
import com.mbiamont.github.service.FetchUserPublicRepositoriesQuery
import com.mbiamont.github.service.remote.graphql.AuthenticationInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module

val graphQlModule = module {

    single(graphQlQualifier) {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(authenticationInterceptorQualifier))
            .build()
    }

    single<Interceptor>(authenticationInterceptorQualifier) { AuthenticationInterceptor(get(bearerTokenQualifier)) }

    single {
        ApolloClient.builder()
            .serverUrl(get<String>(githubGraphQlUrl))
            .okHttpClient(get(graphQlQualifier))
            .build()
    }

    single { FetchUserPublicRepositoriesQuery.builder().build() }

    single { FetchRepositoryWithNameAndOwnerQuery.builder().build() }
}