package com.mbiamont.github.login

interface ILoginController {

    fun onPrepareWebView(callback: (url: String) -> Unit)

    suspend fun onPageLoaded(oAuthCode: String)
}