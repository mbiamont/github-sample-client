package com.mbiamont.github.service.di

import com.mbiamont.github.service.mapper.*
import org.koin.dsl.module

val mapperModule = module {

    single<IRemoteUserMapper> { RemoteUserMapper() }

    single<IRemoteRepositoryMapper> { RemoteRepositoryMapper(get()) }

    single<IRemoteIssueMapper> { RemoteIssueMapper() }
}