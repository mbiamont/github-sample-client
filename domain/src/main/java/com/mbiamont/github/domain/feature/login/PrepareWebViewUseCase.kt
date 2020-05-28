package com.mbiamont.github.domain.feature.login

interface PrepareWebViewUseCase {

    fun prepareWebView(callback: (url: String) -> Unit)
}