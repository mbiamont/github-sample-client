package com.mbiamont.github.repository.details

import com.mbiamont.github.core.onFailure
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.datasource.IRepositoryDataSource
import com.mbiamont.github.domain.feature.repository.details.FetchRepositoryDetailsUseCase
import timber.log.Timber

class RepositoryDetailsInteractor(
    private val repositoryDataSource: IRepositoryDataSource,
    private val presenter: IRepositoryDetailsPresenter
) : FetchRepositoryDetailsUseCase {

    override suspend fun fetchRepositoryDetails(repositoryName: String, ownerLogin: String) {
        repositoryDataSource.getRepositoryWithNameAndOwner(repositoryName, ownerLogin).onSuccess {
            presenter.displayRepositoryDetails(it)
        }.onFailure {
            Timber.e(it)
        }
    }
}