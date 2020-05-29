package com.mbiamont.github.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbiamont.github.core.CoroutineContextProvider
import com.mbiamont.github.core.android.livedata.SingleEventLiveData
import kotlinx.coroutines.launch

class MainViewModel(
    private val contextProvider: CoroutineContextProvider,
    private val controller: IMainController,
    private val presenter: IMainPresenter
) : ViewModel(), IMainView {

    val repositoriesLiveData = MutableLiveData<List<RepositoryExtractViewState>>()

    val errorMessageLiveData = SingleEventLiveData<Int>()

    init {
        presenter.onAttachView(this)
    }

    override fun onCleared() {
        presenter.onDetachView()
    }

    fun onViewReady() = viewModelScope.launch(contextProvider.IO) {
        controller.onViewReady()
    }

    override fun displayRepositoryExtractList(repositories: List<RepositoryExtractViewState>) = repositoriesLiveData.postValue(repositories)

    override fun displayFetchRepositoryError(messageId: Int) = errorMessageLiveData.postValue(messageId)
}