package com.mbiamont.github.datasource.di

import com.mbiamont.github.domain.datasource.IConfigDataSource
import com.mbiamont.github.domain.datasource.IUserDataSource
import com.mbiamont.github.datasource.ConfigDataSource
import com.mbiamont.github.datasource.RepositoryDataSource
import com.mbiamont.github.datasource.UserDataSource
import com.mbiamont.github.domain.datasource.IRepositoryDataSource
import org.koin.dsl.module

val dataSourceModule = module {

    single<IUserDataSource> { UserDataSource(get(), get(), get()) }

    single<IConfigDataSource> { ConfigDataSource(get()) }

    single<IRepositoryDataSource> { RepositoryDataSource(get()) }
}