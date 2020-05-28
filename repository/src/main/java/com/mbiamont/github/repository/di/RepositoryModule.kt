package com.mbiamont.github.repository.di

import com.mbiamont.github.domain.repository.IConfigRepository
import com.mbiamont.github.domain.repository.IUserRepository
import com.mbiamont.github.repository.ConfigRepository
import com.mbiamont.github.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<IUserRepository> { UserRepository(get(), get(), get()) }

    single<IConfigRepository> { ConfigRepository(get()) }
}