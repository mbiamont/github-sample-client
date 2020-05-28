package com.mbiamont.github.di

import com.mbiamont.github.BuildConfig
import com.mbiamont.github.config.ConfigService
import com.mbiamont.github.repository.service.IConfigService
import com.mbiamont.github.service.di.GITHUB_BASE_URL
import org.koin.core.qualifier.named
import org.koin.dsl.module

val configModule = module {

    single<IConfigService> { ConfigService() }

    single(named(GITHUB_BASE_URL)) { BuildConfig.GITHUB_BASE_URL }
}