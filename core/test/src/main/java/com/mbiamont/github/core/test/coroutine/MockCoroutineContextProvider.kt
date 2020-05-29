package com.mbiamont.github.core.test.coroutine

import com.mbiamont.github.core.CoroutineContextProvider
import kotlinx.coroutines.test.TestCoroutineDispatcher

object MockCoroutineContextProvider : CoroutineContextProvider() {
    override val Main = TestCoroutineDispatcher()
    override val IO = TestCoroutineDispatcher()
    override val Default = TestCoroutineDispatcher()
}