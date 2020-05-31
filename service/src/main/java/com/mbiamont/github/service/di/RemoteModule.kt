package com.mbiamont.github.service.di

import com.mbiamont.github.datasource.service.*
import com.mbiamont.github.service.remote.graphql.RemoteForkService
import com.mbiamont.github.service.remote.graphql.RemoteIssueService
import com.mbiamont.github.service.remote.graphql.RemotePullRequestService
import com.mbiamont.github.service.remote.graphql.RemoteRepositoryService
import com.mbiamont.github.service.remote.rest.RemoteRestUserService
import org.koin.dsl.module

val remoteModule = module {

    single<IRemoteUserService> { RemoteRestUserService(get()) }

    single<IRemoteRepositoryService> { RemoteRepositoryService(get(), get()) }

    single<IRemoteIssueService> { RemoteIssueService(get(), get(), get()) }

    single<IRemotePullRequestService> { RemotePullRequestService(get(), get()) }

    single<IRemoteForkService> { RemoteForkService(get(), get()) }
}