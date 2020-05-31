package com.mbiamont.github.repository.forks

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

    private var forks: PaginatedList<Fork> = emptyPaginatedList()

    override suspend fun fetchRepositoryForks(repositoryName: String, ownerLogin: String) {
        forkDataSource.getRepositoryForks(FAKE_REPOSITORY, FAKE_OWNER_LOGIN, forks.lastItemCursor).onSuccess {
            forks += it

            val count = forks.values.size
            val totalCount = forks.totalCount
            val issuesPerWeek = if (!it.hasNext) forks.values.countItemPerWeekSinceOneYear {
                it.createdAt
            } else null

            presenter.displayForks(forks.values, count, totalCount, issuesPerWeek)

            if (it.hasNext) {
                coroutineContext.ensureActive()
                fetchRepositoryForks(repositoryName, ownerLogin)
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