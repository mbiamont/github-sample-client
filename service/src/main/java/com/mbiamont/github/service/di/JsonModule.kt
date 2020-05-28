package com.mbiamont.github.service.di

import com.squareup.moshi.Moshi
import org.koin.dsl.module

val jsonModule = module {

    single { Moshi.Builder().build() }
}