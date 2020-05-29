package com.mbiamont.github.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbiamont.github.core.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(
    private val contextProvider: CoroutineContextProvider,
    private val controller: ISplashController
) : ViewModel() {

    fun onViewReady() = viewModelScope.launch(contextProvider.IO) {
        controller.onViewReady()
    }
}