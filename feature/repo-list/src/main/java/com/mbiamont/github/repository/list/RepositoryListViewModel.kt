package com.mbiamont.github.repository.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbiamont.github.core.CoroutineContextProvider
import com.mbiamont.github.core.android.livedata.SingleEventLiveData
import kotlinx.coroutines.launch

class RepositoryListViewModel(
    private val contextProvider: CoroutineContextProvider,
    private val controller: IRepositoryListController,
    private val presenter: IRepositoryListPresenter
) : ViewModel(), IRepositoryListView {

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

    fun onRepositoryExtractClicked(name: String, ownerLogin: String) = viewModelScope.launch(contextProvider.IO) {

    }

    override fun displayRepositoryExtractList(repositories: List<RepositoryExtractViewState>) = repositoriesLiveData.postValue(repositories)

    override fun displayFetchRepositoryError(messageId: Int) = errorMessageLiveData.postValue(messageId)
}