package com.mbiamont.github.repository.pullrequests

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbiamont.github.core.CoroutineContextProvider
import com.mbiamont.github.domain.navigation.EXTRA_OWNER_LOGIN
import com.mbiamont.github.domain.navigation.EXTRA_REPO_NAME
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.IllegalStateException

class PullRequestsViewModel(
    private val coroutineContextProvider: CoroutineContextProvider,
    private val controller: IPullRequestController,
    private val presenter: IPullRequestsPresenter
): ViewModel(), IPullRequestsView {

    init {
        presenter.onAttachView(this)
    }

    override fun onCleared() {
        presenter.onDetachView()
    }

    fun onViewReady(extras: Bundle?) = viewModelScope.launch(coroutineContextProvider.IO) {

        val repositoryName = extras?.getString(EXTRA_REPO_NAME)
        val ownerLogin = extras?.getString(EXTRA_OWNER_LOGIN)

        if (repositoryName != null && ownerLogin != null) {
            controller.onViewReady(repositoryName, ownerLogin)
        } else {
            Timber.e(IllegalStateException())
        }
    }
}