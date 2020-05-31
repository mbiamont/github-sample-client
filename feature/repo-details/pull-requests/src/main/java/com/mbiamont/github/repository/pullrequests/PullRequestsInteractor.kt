package com.mbiamont.github.repository.pullrequests

import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.core.emptyPaginatedList
import com.mbiamont.github.core.extensions.countItemPerWeekSinceOneYear
import com.mbiamont.github.core.extensions.minusYears
import com.mbiamont.github.core.onFailure
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.datasource.IPullRequestDataSource
import com.mbiamont.github.domain.entity.PullRequest
import com.mbiamont.github.domain.feature.repository.details.pullrequests.FetchRepositoryPullRequestsUseCase
import kotlinx.coroutines.ensureActive
import timber.log.Timber
import java.util.*
import kotlin.coroutines.coroutineContext

class PullRequestsInteractor(
    private val presenter: IPullRequestsPresenter,
    private val pullRequestDataSource: IPullRequestDataSource
) : FetchRepositoryPullRequestsUseCase {

    private val sinceDate = Date().minusYears(1)

    private var pullRequests: PaginatedList<PullRequest> = emptyPaginatedList()

    override suspend fun fetchRepositoryPullRequests(repositoryName: String, ownerLogin: String) {
        presenter.displayTimeSerieProgress(true)
        pullRequestDataSource.getRepositoryPullRequests(repositoryName, ownerLogin, pullRequests.lastItemCursor).onSuccess {
            val oldestItem = it.values.firstOrNull()?.createdAt

            pullRequests += it

            presenter.displayPullRequests(pullRequests.values)

            if (it.hasNext && oldestItem?.let { oldestItem.timeInMillis > sinceDate.time } == true) {
                coroutineContext.ensureActive()
                fetchRepositoryPullRequests(repositoryName, ownerLogin)
            } else {
                val issuesPerWeek = pullRequests.values.countItemPerWeekSinceOneYear { issue ->
                    issue.createdAt
                }

                presenter.displayTimeSerie(issuesPerWeek)
                presenter.displayTimeSerieProgress(false)
            }

        }.onFailure {
            Timber.e(it)
            //TODO SHOW ERROR
        }
    }
}