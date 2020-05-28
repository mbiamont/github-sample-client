package com.mbiamont.github.di

import com.mbiamont.github.core.android.INavigator
import com.mbiamont.github.navigation.AndroidNavigator
import com.mbiamont.github.GithubRouter
import com.mbiamont.github.navigation.IRouter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val navigationModule = module {
    single<IRouter> { GithubRouter() }

    single<INavigator> { AndroidNavigator(get(), androidContext()) }
}