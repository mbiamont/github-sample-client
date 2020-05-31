package com.mbiamont.github.repository.forks

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbiamont.github.core.CoroutineContextProvider
import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState
import com.mbiamont.github.domain.navigation.EXTRA_OWNER_LOGIN
import com.mbiamont.github.domain.navigation.EXTRA_REPO_NAME
import kotlinx.coroutines.launch
import org.koin.ext.getOrCreateScope
import timber.log.Timber
import java.lang.IllegalStateException

class ForksViewModel(
    private val coroutineContextProvider: CoroutineContextProvider,
) : ViewModel(), IForksView {

    val forkListLiveData = MutableLiveData<List<ForkViewState>>()
    val progressLiveData = MutableLiveData<ProgressViewState>()
    val timeSerieLiveData = MutableLiveData<TimeSerieViewState>()

    private var initialized = false
    private val koinScope = getOrCreateScope()
    private val controller: IForksController
    private val presenter: IForksPresenter

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

    override fun displayForkList(forkListViewState: List<ForkViewState>) = forkListLiveData.postValue(forkListViewState)

    override fun displayTimeSerieProgress(progressViewState: ProgressViewState) = progressLiveData.postValue(progressViewState)

    override fun displayTimeSerie(timeSerieViewState: TimeSerieViewState) = timeSerieLiveData.postValue(timeSerieViewState)
}