package com.mbiamont.github.repository.issues

import com.mbiamont.github.core.onFailure
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.datasource.IIssueDataSource
import com.mbiamont.github.domain.feature.repository.details.issues.FetchRepositoryIssuesUseCase
import timber.log.Timber
import java.util.*

class IssuesInteractor(
    private val issueDataSource: IIssueDataSource,
    private val presenter: IIssuesPresenter
) : FetchRepositoryIssuesUseCase {

    override suspend fun fetchRepositoryIssues(repositoryName: String, ownerLogin: String) {
        val date = Date() //TODO

        issueDataSource.getRepositoryIssues(repositoryName, ownerLogin, date).onSuccess {
            Timber.i("[MELVIN] ISSUES: ${it.size}")
            it.forEach {
                Timber.i("[MELVIN][${it.state.name}] ${it.title}")
            }
        }.onFailure {
            Timber.e(it)
        }
    }
}