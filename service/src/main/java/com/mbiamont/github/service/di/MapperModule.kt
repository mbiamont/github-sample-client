package com.mbiamont.github.service.di

import com.mbiamont.github.service.mapper.IRemoteRepositoryMapper
import com.mbiamont.github.service.mapper.IRemoteUserMapper
import com.mbiamont.github.service.mapper.RemoteRepositoryMapper
import com.mbiamont.github.service.mapper.RemoteUserMapper
import org.koin.dsl.module

val mapperModule = module {
    single<IRemoteUserMapper> { RemoteUserMapper() }

    single<IRemoteRepositoryMapper> { RemoteRepositoryMapper(get()) }
}