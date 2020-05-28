package com.mbiamont.github.core.android.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


fun <T> LifecycleOwner.observe(liveData: LiveData<T>) = ObserverWrapper(this, liveData)

infix fun <T, R> ObserverWrapper<T>.with(block: (T) -> R) = liveData.observe(lifecycle, Observer { block(it) })

class ObserverWrapper<T>(val lifecycle: LifecycleOwner, val liveData: LiveData<T>)