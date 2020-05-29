package com.mbiamont.github.main

import com.mbiamont.github.core.onFailure
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.datasource.IIssueDataSource
import com.mbiamont.github.domain.datasource.IRepositoryDataSource
import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.domain.feature.main.FetchUserPublicRepositoriesUseCase
import timber.log.Timber

class MainInteractor(
    private val repositoryDataSource: IRepositoryDataSource,
    private val issueDataSource: IIssueDataSource,
    private val presenter: IMainPresenter
) : FetchUserPublicRepositoriesUseCase {

    override suspend fun fetchUserPublicRepositories() {
        repositoryDataSource.getUserPublicRepositories().onSuccess {
            Timber.i("[MELVIN] REPOSITORY COUNT : ${it.size}")
            it.forEach {
                Timber.i("[MELVIN] ${it.starsCount} ⭐︎ - ${it.name}")
            }
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