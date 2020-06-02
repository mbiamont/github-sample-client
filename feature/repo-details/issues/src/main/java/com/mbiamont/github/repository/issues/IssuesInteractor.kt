package com.mbiamont.github.repository.issues

import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.core.emptyPaginatedList
import com.mbiamont.github.core.extensions.countItemPerWeekSinceOneYear
import com.mbiamont.github.core.extensions.minusYears
import com.mbiamont.github.core.onFailure
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.datasource.IIssueDataSource
import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.domain.feature.repository.details.issues.FetchRepositoryIssuesUseCase
import kotlinx.coroutines.ensureActive
import timber.log.Timber
import java.util.*
import kotlin.coroutines.coroutineContext

class IssuesInteractor(
    private val issueDataSource: IIssueDataSource,
    private val presenter: IIssuesPresenter
) : FetchRepositoryIssuesUseCase {

    private val sinceDate = Date().minusYears(1)

    private var issues: PaginatedList<Issue> = emptyPaginatedList()

    override suspend fun fetchRepositoryIssues(repositoryName: String, ownerLogin: String) {
        presenter.displayTimeSerieProgress(true)
        issueDataSource.getRepositoryIssues(repositoryName, ownerLogin, issues.lastItemCursor).onSuccess {
            val oldestItem = it.values.firstOrNull()?.createdAt

            issues += it

            presenter.displayIssues(issues.values)

            if (it.hasNext && oldestItem?.let { oldestItem.timeInMillis > sinceDate.time } == true) {
                coroutineContext.ensureActive()
                fetchRepositoryIssues(repositoryName, ownerLogin)
            } else {
                val issuesPerWeek = issues.values.countItemPerWeekSinceOneYear { issue ->
                    issue.createdAt
                }

                presenter.displayTimeSerie(issuesPerWeek)
                presenter.displayTimeSerieProgress(false)
            }

        }.onFailure {
            Timber.e(it)
            presenter.displayTimeSerieProgress(false)
            presenter.displayFetchIssuesError()
        }
    }
}

