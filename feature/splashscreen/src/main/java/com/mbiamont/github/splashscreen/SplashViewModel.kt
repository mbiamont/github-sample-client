package com.mbiamont.github.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(
    private val controller: ISplashController
) : ViewModel() {

    fun onViewReady() = viewModelScope.launch(Dispatchers.IO) {
        controller.onViewReady()
    }
}