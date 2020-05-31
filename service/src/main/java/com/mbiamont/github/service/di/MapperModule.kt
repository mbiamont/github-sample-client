package com.mbiamont.github.service.di

import com.mbiamont.github.service.mapper.*
import org.koin.dsl.module

val mapperModule = module {

    single<IRemoteUserMapper> { RemoteUserMapper() }

    single<IRemoteDateMapper> { RemoteDateMapper() }

    single<IRemoteLanguageMapper> { RemoteLanguageMapper() }

    single<IRemoteRepositoryMapper> { RemoteRepositoryMapper(get(), get()) }

    single<IRemoteIssueMapper> { RemoteIssueMapper(get(), get()) }

    single<IRemotePullRequestMapper> { RemotePullRequestMapper(get(), get()) }

    single<IRemoteForkMapper> { RemoteForkMapper(get(), get()) }
}