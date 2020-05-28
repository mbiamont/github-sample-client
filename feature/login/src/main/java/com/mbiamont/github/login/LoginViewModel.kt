package com.mbiamont.github.login

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbiamont.github.core.android.livedata.SingleEventLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel(
    private val controller: ILoginController,
    private val presenter: ILoginPresenter
) : ViewModel(), ILoginView {

    val errorMessageLiveData = SingleEventLiveData<Int>()

    init {
        presenter.onAttachView(this)
    }

    override fun onCleared() {
        presenter.onDetachView()
    }

    fun onPrepareWebView(callback: (url: String) -> Unit) = controller.onPrepareWebView(callback)

    fun onPageLoaded(uri: Uri): Boolean {
        val codeQueryParam = uri.getQueryParameter("code") ?: ""

        if (codeQueryParam.isEmpty()) {
            Timber.e("No code param")
            return false
        }

        viewModelScope.launch(Dispatchers.IO) {
            controller.onPageLoaded(codeQueryParam)
        }

        return true
    }

    override fun displayLoginErrorMessage(message: Int) = errorMessageLiveData.postValue(message)
}