package com.mbiamont.github.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbiamont.github.core.CoroutineContextProvider
import kotlinx.coroutines.launch

class MainViewModel(
    private val contextProvider: CoroutineContextProvider,
    private val controller: IMainController
): ViewModel() {

    fun onViewReady() = viewModelScope.launch(contextProvider.IO) {
        controller.onViewReady()
    }
}