package com.mbiamont.github.service.prefs

import android.content.SharedPreferences
import java.lang.Exception

class StringPref(
    key: String,
    private val preferences: SharedPreferences,
    private val defaultValue: String
) : IPreference<String>(key) {

    override fun get(): String? = try {
        preferences.getString(key, defaultValue) ?: defaultValue
    } catch (e: Exception) {
        defaultValue
    }

    override fun set(value: String) = preferences.edit()
        .putString(key, value)
        .apply()

    override fun isSet() = preferences.contains(key)

    override fun delete() = preferences.edit()
        .remove(key)
        .apply()
}