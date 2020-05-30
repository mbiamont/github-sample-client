package com.mbiamont.github.repository.list

import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.core.emptyPaginatedList
import com.mbiamont.github.core.onFailure
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.datasource.IIssueDataSource
import com.mbiamont.github.domain.datasource.IRepositoryDataSource
import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.domain.entity.RepositoryExtract
import com.mbiamont.github.domain.feature.repository.list.FetchUserPublicRepositoriesUseCase
import timber.log.Timber

class RepositoryListInteractor(
    private val repositoryDataSource: IRepositoryDataSource,
    private val presenter: IRepositoryListPresenter
) : FetchUserPublicRepositoriesUseCase {

    private var repositories: PaginatedList<RepositoryExtract> = emptyPaginatedList()

    override suspend fun fetchUserPublicRepositories() {
        Timber.i("[MELVIN] fetchUserPublicRepositories(${repositories.lastItemCursor})")
        repositoryDataSource.getUserPublicRepositories(repositories.lastItemCursor).onSuccess {
            repositories += it

            presenter.displayRepositoryExtracts(repositories.values)
        }.onFailure {
            Timber.e(it)
        }
    }
}