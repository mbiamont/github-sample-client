package com.mbiamont.github.di

import com.mbiamont.github.login.di.loginModule
import com.mbiamont.github.repository.di.repositoryModule
import com.mbiamont.github.service.di.cacheModule
import com.mbiamont.github.service.di.jsonModule
import com.mbiamont.github.service.di.preferencesModule
import com.mbiamont.github.service.di.restModule
import com.mbiamont.github.splashscreen.di.splashModule

val applicationModules = listOf(
    configModule,
    jsonModule,
    preferencesModule,
    cacheModule,
    restModule,
    repositoryModule,
    navigationModule,
    coroutineModule,

    splashModule,
    loginModule
)