package com.mbiamont.github.service.di

import android.content.Context
import com.mbiamont.github.core.qualifier.bearerTokenQualifier
import com.mbiamont.github.service.prefs.IPreference
import com.mbiamont.github.service.prefs.PREFERENCES
import com.mbiamont.github.service.prefs.PREF_BEARER_TOKEN_NAME
import com.mbiamont.github.service.prefs.StringPref
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferencesModule = module {

    single<IPreference<String>>(bearerTokenQualifier) { StringPref(PREF_BEARER_TOKEN_NAME, get(), "") }

    single { androidContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE) }
}