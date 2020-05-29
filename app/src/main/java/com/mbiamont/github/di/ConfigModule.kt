package com.mbiamont.github.di

import com.mbiamont.github.BuildConfig
import com.mbiamont.github.config.ConfigService
import com.mbiamont.github.core.qualifier.githubBaseUrl
import com.mbiamont.github.datasource.service.IConfigService
import org.koin.dsl.module

val configModule = module {

    single<IConfigService> { ConfigService() }

    single(githubBaseUrl) { BuildConfig.GITHUB_BASE_URL }
}