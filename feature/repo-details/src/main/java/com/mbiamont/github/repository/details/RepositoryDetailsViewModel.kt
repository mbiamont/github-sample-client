package com.mbiamont.github.repository.details

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbiamont.github.core.CoroutineContextProvider
import com.mbiamont.github.core.android.livedata.SingleEventLiveData
import com.mbiamont.github.domain.navigation.EXTRA_REPO_NAME
import com.mbiamont.github.domain.navigation.EXTRA_OWNER_LOGIN
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.IllegalStateException

class RepositoryDetailsViewModel(
    private val contextProvider: CoroutineContextProvider,
    private val controller: IRepositoryDetailsController,
    private val presenter: IRepositoryDetailsPresenter
) : ViewModel(), IRepositoryDetailsView {

    val repositoryDetailsLiveData = MutableLiveData<RepositoryDetailsViewState>()
    val errorMessageLiveData = SingleEventLiveData<Int>()

    init {
        presenter.onAttachView(this)
    }

    override fun onCleared() {
        presenter.onDetachView()
    }

    fun onViewReady(extras: Bundle?) = viewModelScope.launch(contextProvider.IO) {
        val repositoryName = extras?.getString(EXTRA_REPO_NAME)
        val ownerLogin = extras?.getString(EXTRA_OWNER_LOGIN)

        if (repositoryName != null && ownerLogin != null) {
            controller.onViewReady(repositoryName, ownerLogin)
        } else {
            Timber.e(IllegalStateException())
        }
    }

    override fun displayRepositoryDetails(repositoryDetails: RepositoryDetailsViewState) {
        repositoryDetailsLiveData.postValue(repositoryDetails)
    }

    override fun displayErrorMessage(message: Int) {
        errorMessageLiveData.postValue(message)
    }
}