package com.mbiamont.github.service.di

import com.mbiamont.github.core.qualifier.bearerTokenQualifier
import com.mbiamont.github.datasource.service.ICacheUserService
import com.mbiamont.github.service.cache.CacheUserService
import org.koin.dsl.module

val cacheModule = module {

    single<ICacheUserService> { CacheUserService(get(bearerTokenQualifier)) }
}