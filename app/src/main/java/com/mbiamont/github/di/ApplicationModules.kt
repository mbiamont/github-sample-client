package com.mbiamont.github.di

import com.mbiamont.github.login.di.loginModule
import com.mbiamont.github.datasource.di.dataSourceModule
import com.mbiamont.github.repository.list.di.repositoryListModule
import com.mbiamont.github.service.di.*
import com.mbiamont.github.splashscreen.di.splashModule

val applicationModules = listOf(
    configModule,
    jsonModule,
    preferencesModule,
    cacheModule,
    restModule,
    remoteModule,
    graphQlModule,
    dataSourceModule,
    mapperModule,
    navigationModule,
    coroutineModule,

    splashModule,
    loginModule,
    repositoryListModule
)