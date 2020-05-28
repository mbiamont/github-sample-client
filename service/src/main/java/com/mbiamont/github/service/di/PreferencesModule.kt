package com.mbiamont.github.service.di

import android.content.Context
import com.mbiamont.github.service.prefs.PREFERENCES
import com.mbiamont.github.service.prefs.PREF_BEARER_TOKEN_NAME
import com.mbiamont.github.service.prefs.StringPref
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val preferencesModule = module {

    single(named(PREF_BEARER_TOKEN_NAME)) { StringPref(PREF_BEARER_TOKEN_NAME, get(), "") }

    single { androidContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE) }
}