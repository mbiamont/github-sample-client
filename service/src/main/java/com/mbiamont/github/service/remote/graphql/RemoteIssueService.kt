package com.mbiamont.github.service.remote.graphql

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.fetcher.ApolloResponseFetchers
import com.mbiamont.github.core.Monad
import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.datasource.service.IRemoteIssueService
import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.domain.exception.NetworkException
import com.mbiamont.github.service.graphql.FetchRepositoryIssuesQuery
import com.mbiamont.github.service.mapper.IRemoteIssueMapper
import java.lang.IllegalStateException
import java.util.*

class RemoteIssueService(
    private val apolloClient: ApolloClient,
    private val remoteIssueMapper: IRemoteIssueMapper
) : IRemoteIssueService {

    override suspend fun getRepositoryIssues(
        repositoryName: String,
        ownerLogin: String,
        since: Date,
        afterCursor: String?
    ): Monad<PaginatedList<Issue>> {
        val query = FetchRepositoryIssuesQuery.builder()
            .name(repositoryName)
            .ownerLogin(ownerLogin)
            .afterCursor(afterCursor)
            .size(SIZE_REPOSITORY_PER_PAGE)
            .build()

        lateinit var response: Response<FetchRepositoryIssuesQuery.Data>
        try {
            response = apolloClient.query(query)
                .responseFetcher(ApolloResponseFetchers.NETWORK_FIRST)
                .toDeferred()
                .await()
        } catch (e: Exception) {
            return failure(NetworkException(e.message))
        }

        response.data?.repository()?.let { repository ->
            val issues = mutableListOf<Issue>().apply {
                repository.issues().edges()?.mapNotNull { it.node()?.let { remoteIssueMapper.map(it) } }?.let {
                    addAll(it)
                }
            }

            val paginatedList = PaginatedList(
                issues,
                repository.issues().pageInfo().hasNextPage(),
                repository.issues().totalCount(),
                repository.issues().edges()?.lastOrNull()?.cursor()
            )

            return success(paginatedList)
        }

        return failure(IllegalStateException()) //TODO MORE INFO
    }

    companion object {
        const val SIZE_REPOSITORY_PER_PAGE = 50
    }
}