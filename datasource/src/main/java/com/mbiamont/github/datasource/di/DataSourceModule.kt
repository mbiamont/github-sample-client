package com.mbiamont.github.datasource.di

import com.mbiamont.github.datasource.*
import com.mbiamont.github.domain.datasource.*
import org.koin.dsl.module

val dataSourceModule = module {

    single<IUserDataSource> { UserDataSource(get(), get(), get()) }

    single<IConfigDataSource> { ConfigDataSource(get()) }

    single<IRepositoryDataSource> { RepositoryDataSource(get()) }

    single<IIssueDataSource> { IssueDataSource(get()) }

    single<IPullRequestDataSource> { PullRequestDataSource(get()) }

    single<IForkDataSource> { ForkDataSource(get()) }
}