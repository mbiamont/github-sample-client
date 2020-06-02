package com.mbiamont.github.repository.forks

import androidx.annotation.VisibleForTesting
import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.core.emptyPaginatedList
import com.mbiamont.github.core.extensions.countItemPerWeekSinceOneYear
import com.mbiamont.github.core.extensions.minusYears
import com.mbiamont.github.core.onFailure
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.datasource.IForkDataSource
import com.mbiamont.github.domain.entity.Fork
import com.mbiamont.github.domain.feature.repository.details.forks.FetchRepositoryForksUseCase
import kotlinx.coroutines.ensureActive
import timber.log.Timber
import java.util.*
import kotlin.coroutines.coroutineContext

class ForksInteractor(
    private val presenter: IForksPresenter,
    private val forkDataSource: IForkDataSource
) : FetchRepositoryForksUseCase {

    private val sinceDate = Date().minusYears(1)

    private var forks: PaginatedList<Fork> = emptyPaginatedList()

    override suspend fun fetchRepositoryForks(repositoryName: String, ownerLogin: String) {
        presenter.displayTimeSerieProgress(true)
        forkDataSource.getRepositoryForks(repositoryName, ownerLogin, forks.lastItemCursor).onSuccess {
            val oldestItem = it.values.firstOrNull()?.createdAt
            forks += it

            presenter.displayForks(forks.values)

            if (it.hasNext && oldestItem?.let { oldestItem.timeInMillis > sinceDate.time } == true) {
                coroutineContext.ensureActive()
                fetchRepositoryForks(repositoryName, ownerLogin)
            } else {
                val issuesPerWeek = forks.values.countItemPerWeekSinceOneYear { issue ->
                    issue.createdAt
                }

                presenter.displayTimeSerie(issuesPerWeek)
                presenter.displayTimeSerieProgress(false)
            }

        }.onFailure {
            Timber.e(it)
            presenter.displayTimeSerieProgress(false)
            presenter.displayFetchForksError()
        }
    }
}