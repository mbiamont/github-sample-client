package com.mbiamont.github.repository.pullrequests

import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.core.emptyPaginatedList
import com.mbiamont.github.core.extensions.countItemPerWeekSinceOneYear
import com.mbiamont.github.core.onFailure
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.datasource.IPullRequestDataSource
import com.mbiamont.github.domain.entity.PullRequest
import com.mbiamont.github.domain.feature.repository.details.pullrequests.FetchRepositoryPullRequestsUseCase
import kotlinx.coroutines.ensureActive
import timber.log.Timber
import kotlin.coroutines.coroutineContext

class PullRequestsInteractor(
    private val presenter: IPullRequestsPresenter,
    private val pullRequestDataSource: IPullRequestDataSource
) : FetchRepositoryPullRequestsUseCase {

    private var pullRequests: PaginatedList<PullRequest> = emptyPaginatedList()

    override suspend fun fetchRepositoryPullRequests(repositoryName: String, ownerLogin: String) {
        pullRequestDataSource.getRepositoryPullRequests(FAKE_REPOSITORY, FAKE_OWNER_LOGIN, pullRequests.lastItemCursor).onSuccess {
            pullRequests += it

            val count = pullRequests.values.size
            val totalCount = pullRequests.totalCount

            val issuesPerWeek = if (!it.hasNext) pullRequests.values.countItemPerWeekSinceOneYear {pullRequest ->
                pullRequest.createdAt
            } else null

            presenter.displayPullRequests(pullRequests.values, count, totalCount, issuesPerWeek)

            if (it.hasNext) {
                coroutineContext.ensureActive()
                fetchRepositoryPullRequests(repositoryName, ownerLogin)
            }

        }.onFailure {
            Timber.e(it)
            //TODO SHOW ERROR
        }
    }

    companion object {
        const val FAKE_REPOSITORY = "kotlinx.coroutines"
        const val FAKE_OWNER_LOGIN = "Kotlin"
    }
}