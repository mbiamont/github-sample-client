package com.mbiamont.github.core.android.di

import com.mbiamont.github.core.android.provider.ColorProvider
import com.mbiamont.github.core.android.provider.DateUtilsProvider
import com.mbiamont.github.core.android.provider.IColorProvider
import com.mbiamont.github.core.android.provider.IDateUtilsProvider
import org.koin.dsl.module

val providerModule = module {

    single<IColorProvider> { ColorProvider() }

    single<IDateUtilsProvider> { DateUtilsProvider() }
}