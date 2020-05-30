package com.mbiamont.github.login

import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.mbiamont.github.core.android.BaseActivity
import com.mbiamont.github.core.android.extensions.observe
import com.mbiamont.github.core.android.extensions.with
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class LoginActivity : BaseActivity(R.layout.activity_login) {

    private val viewModel: LoginViewModel by viewModel()

    private lateinit var webviewUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupObservers()
        setupWebView()
    }

    private fun setupWebView() = with(loginWebView) {
        settings.javaScriptEnabled = true
        webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                request?.url?.let { url ->
                    return viewModel.onPageLoaded(url)
                }

                return super.shouldOverrideUrlLoading(view, request)
            }
        }

        viewModel.onPrepareWebView { url ->
            webviewUrl = url
            resetWebView()
        }
    }

    private fun resetWebView() {
        CookieManager.getInstance().removeAllCookies { }
        loginWebView.loadUrl(webviewUrl)
    }

    private fun setupObservers() {
        observe(viewModel.errorMessageLiveData) with {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            resetWebView()
        }
    }
}