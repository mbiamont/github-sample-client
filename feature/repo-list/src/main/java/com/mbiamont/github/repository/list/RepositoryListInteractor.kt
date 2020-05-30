package com.mbiamont.github.repository.list

import com.mbiamont.github.core.onFailure
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.datasource.IIssueDataSource
import com.mbiamont.github.domain.datasource.IRepositoryDataSource
import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.domain.feature.repository.list.FetchUserPublicRepositoriesUseCase
import timber.log.Timber

class RepositoryListInteractor(
    private val repositoryDataSource: IRepositoryDataSource,
    private val issueDataSource: IIssueDataSource,
    private val presenter: IRepositoryListPresenter
) : FetchUserPublicRepositoriesUseCase {

    override suspend fun fetchUserPublicRepositories() {
        repositoryDataSource.getUserPublicRepositories().onSuccess {
            presenter.displayRepositoryExtracts(it)
        }.onFailure {
            Timber.e(it)
        }

        repositoryDataSource.getRepositoryWithNameAndOwner("foo", "bar").onSuccess {
            Timber.i("[MELVIN][DETAILS] $it")
        }.onFailure {
            Timber.e(it)
        }

        issueDataSource.getRepositoryIssues("foo", "bar").onSuccess {
            it.forEach { issue ->
                val status = when(issue.state){
                    Issue.State.OPEN -> "OPEN"
                    Issue.State.CLOSED -> "CLOSED"
                    Issue.State.UNKNOWN -> "UNKNOWN"
                }
                Timber.i("[MELVIN][ISSUE][$status] ${issue.title}")
            }
        }.onFailure {
            Timber.e(it)
        }
    }
}