package com.mbiamont.github.service.di

import com.mbiamont.github.datasource.service.IRemoteIssueService
import com.mbiamont.github.datasource.service.IRemoteRepositoryService
import com.mbiamont.github.datasource.service.IRemoteUserService
import com.mbiamont.github.service.remote.graphql.RemoteIssueService
import com.mbiamont.github.service.remote.graphql.RemoteRepositoryService
import com.mbiamont.github.service.remote.rest.RemoteRestUserService
import org.koin.dsl.module

val remoteModule = module {

    single<IRemoteUserService> { RemoteRestUserService(get()) }

    single<IRemoteRepositoryService> { RemoteRepositoryService(get(), get()) }

    single<IRemoteIssueService> { RemoteIssueService(get(), get(), get()) }
}