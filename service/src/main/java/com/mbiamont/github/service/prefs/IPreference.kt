package com.mbiamont.github.service.prefs

abstract class IPreference<T>(val key: String) {

    abstract fun get(): T?

    abstract fun set(value : T)

    abstract fun isSet() : Boolean

    abstract fun delete()
}