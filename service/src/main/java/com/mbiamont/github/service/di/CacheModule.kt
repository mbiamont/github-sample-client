package com.mbiamont.github.service.di

import com.mbiamont.github.repository.service.ICacheUserService
import com.mbiamont.github.service.cache.CacheUserService
import com.mbiamont.github.service.prefs.PREF_BEARER_TOKEN_NAME
import org.koin.core.qualifier.named
import org.koin.dsl.module

val cacheModule = module {

    single<ICacheUserService> { CacheUserService(get(named(PREF_BEARER_TOKEN_NAME))) }
}