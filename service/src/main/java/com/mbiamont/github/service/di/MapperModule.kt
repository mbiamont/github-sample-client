package com.mbiamont.github.service.di

import com.mbiamont.github.service.mapper.IRemoteRepositoryMapper
import com.mbiamont.github.service.mapper.RemoteRepositoryMapper
import org.koin.dsl.module

val mapperModule = module {
    single< IRemoteRepositoryMapper> { RemoteRepositoryMapper() }
}