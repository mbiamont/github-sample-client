package com.mbiamont.github.di

import com.mbiamont.github.BuildConfig
import com.mbiamont.github.config.ConfigService
import com.mbiamont.github.core.android.ColorProvider
import com.mbiamont.github.core.android.IColorProvider
import com.mbiamont.github.core.qualifier.githubGraphQlUrl
import com.mbiamont.github.core.qualifier.githubRestBaseUrl
import com.mbiamont.github.datasource.service.IConfigService
import org.koin.dsl.module

val configModule = module {

    single<IConfigService> { ConfigService() }

    single(githubRestBaseUrl) { BuildConfig.GITHUB_REST_BASE_URL }

    single(githubGraphQlUrl) { BuildConfig.GITHUB_GRAPH_QL_URL }

    single<IColorProvider> { ColorProvider() }
}