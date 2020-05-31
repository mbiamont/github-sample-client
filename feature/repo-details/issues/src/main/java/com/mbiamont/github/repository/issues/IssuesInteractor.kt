package com.mbiamont.github.repository.issues

import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.core.emptyPaginatedList
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

    private val sinceDate = Date().minusYears(1) //TODO SHOULD BE 1 HERE

    private var issues: PaginatedList<Issue> = emptyPaginatedList()

    override suspend fun fetchRepositoryIssues(repositoryName: String, ownerLogin: String) {
        issueDataSource.getRepositoryIssues(FAKE_REPOSITORY, FAKE_OWNER_LOGIN, sinceDate, issues.lastItemCursor).onSuccess {
            issues += it

            val count = issues.values.size
            val totalCount = issues.totalCount
            val issuesPerWeek = if (!it.hasNext) getIssuesPerWeek(issues.values) else null

            presenter.displayIssues(issues.values, count, totalCount, issuesPerWeek)

            if (it.hasNext) {
                coroutineContext.ensureActive()
                fetchRepositoryIssues(repositoryName, ownerLogin)
            }

        }.onFailure {
            Timber.e(it)
            //TODO SHOW ERROR
        }
    }

    fun getIssuesPerWeek(issuesList: List<Issue>): Array<Int> {
        val since = Calendar.getInstance().apply {
            time = sinceDate
        }

        val sinceWeek = since.get(Calendar.WEEK_OF_YEAR)
        val sinceYear = since.get(Calendar.YEAR)

        val array = Array(52) { 0 }

        issuesList.forEach { issue ->
            val weekOfYear = issue.createdAt.get(Calendar.WEEK_OF_YEAR)
            val year = issue.createdAt.get(Calendar.YEAR)

            val diffYear = year - sinceYear
            val diffWeek = weekOfYear - sinceWeek

            val adjustedWeek = diffWeek + (diffYear * 52)


            if (adjustedWeek in 0..51) {
                array[adjustedWeek]++
            }
        }

        return array
    }

    companion object {
        const val FAKE_REPOSITORY = "kotlinx.coroutines"
        const val FAKE_OWNER_LOGIN = "Kotlin"
    }
}

