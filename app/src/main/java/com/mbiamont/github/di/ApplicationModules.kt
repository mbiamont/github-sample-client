package com.mbiamont.github.di

import com.mbiamont.github.core.android.di.providerModule
import com.mbiamont.github.login.di.loginModule
import com.mbiamont.github.datasource.di.dataSourceModule
import com.mbiamont.github.repository.details.di.repositoryDetailsModule
import com.mbiamont.github.repository.forks.di.forksModule
import com.mbiamont.github.repository.issues.di.issuesModule
import com.mbiamont.github.repository.list.di.repositoryListModule
import com.mbiamont.github.repository.pullrequests.di.pullRequestsModule
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
    providerModule,
    splashModule,
    loginModule,
    repositoryListModule,
    repositoryDetailsModule,
    issuesModule,
    pullRequestsModule,
    forksModule
)