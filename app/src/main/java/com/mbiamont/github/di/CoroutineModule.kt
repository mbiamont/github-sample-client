package com.mbiamont.github.di

import com.mbiamont.github.core.CoroutineContextProvider
import org.koin.dsl.module

val coroutineModule = module {

    single { CoroutineContextProvider() }
}