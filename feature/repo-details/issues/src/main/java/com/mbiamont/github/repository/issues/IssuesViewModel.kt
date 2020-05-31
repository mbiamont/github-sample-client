package com.mbiamont.github.repository.issues

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbiamont.github.core.CoroutineContextProvider
import com.mbiamont.github.domain.navigation.EXTRA_OWNER_LOGIN
import com.mbiamont.github.domain.navigation.EXTRA_REPO_NAME
import kotlinx.coroutines.launch
import org.koin.ext.getOrCreateScope
import timber.log.Timber
import java.lang.IllegalStateException

class IssuesViewModel(
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel(), IIssuesView {

    val issuesViewStateLiveData = MutableLiveData<IssuesViewState>()

    private var initialized = false
    private val koinScope = getOrCreateScope()
    private val controller: IIssuesController
    private val presenter: IIssuesPresenter

    init {
        controller = koinScope.get()
        presenter = koinScope.get()

        presenter.onAttachView(this)
    }

    override fun onCleared() {
        presenter.onDetachView()
        koinScope.close()
    }

    fun onViewReady(extras: Bundle?) = viewModelScope.launch(coroutineContextProvider.IO) {
        if (initialized) {
            return@launch
        }
        initialized = true

        val repositoryName = extras?.getString(EXTRA_REPO_NAME)
        val ownerLogin = extras?.getString(EXTRA_OWNER_LOGIN)

        if (repositoryName != null && ownerLogin != null) {
            controller.onViewReady(repositoryName, ownerLogin)
        } else {
            Timber.e(IllegalStateException())
        }
    }

    override fun displayIssueList(issuesViewState: IssuesViewState) {
        issuesViewStateLiveData.postValue(issuesViewState)
    }
}