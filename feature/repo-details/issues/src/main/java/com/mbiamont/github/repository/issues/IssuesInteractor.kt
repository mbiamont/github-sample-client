package com.mbiamont.github.repository.issues

import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.core.emptyPaginatedList
import com.mbiamont.github.core.extensions.minusYears
import com.mbiamont.github.core.onFailure
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.datasource.IIssueDataSource
import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.domain.entity.RepositoryExtract
import com.mbiamont.github.domain.feature.repository.details.issues.FetchRepositoryIssuesUseCase
import timber.log.Timber
import java.time.LocalDate
import java.util.*

class IssuesInteractor(
    private val issueDataSource: IIssueDataSource,
    private val presenter: IIssuesPresenter
) : FetchRepositoryIssuesUseCase {

    private val sinceDate = Date().minusYears(1)

    private var issues: PaginatedList<Issue> = emptyPaginatedList()

    override suspend fun fetchRepositoryIssues(repositoryName: String, ownerLogin: String) {
        issueDataSource.getRepositoryIssues(FAKE_REPOSITORY, FAKE_OWNER_LOGIN, sinceDate, issues.lastItemCursor).onSuccess {
            issues += it

            it.values.let {
                Timber.i("[MELVIN] ISSUES: ${it.size}")
                it.forEach {
                    Timber.i("[MELVIN][${it.state.name}] ${it.title}")
                }
            }

            presenter.displayIssues(issues.values)

            if(it.hasNext){
                fetchRepositoryIssues(repositoryName, ownerLogin)
            }

        }.onFailure {
            Timber.e(it)
        }

    }

    companion object {
        const val FAKE_REPOSITORY = "kotlinx.coroutines"
        const val FAKE_OWNER_LOGIN = "Kotlin"
    }
}