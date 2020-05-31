package com.mbiamont.github.service.remote.graphql

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.fetcher.ApolloResponseFetchers
import com.mbiamont.github.core.Monad
import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.datasource.service.IRemotePullRequestService
import com.mbiamont.github.domain.entity.PullRequest
import com.mbiamont.github.service.graphql.FetchRepositoryPullRequestsQuery
import com.mbiamont.github.service.mapper.IRemotePullRequestMapper
import java.lang.IllegalStateException

class RemotePullRequestService(
    private val apolloClient: ApolloClient,
    private val pullRequestMapper: IRemotePullRequestMapper
): IRemotePullRequestService {

    override suspend fun getRepositoryPullRequests(
        repositoryName: String,
        ownerLogin: String,
        afterCursor: String?
    ): Monad<PaginatedList<PullRequest>> {
        val query = FetchRepositoryPullRequestsQuery.builder()
            .name(repositoryName)
            .ownerLogin(ownerLogin)
            .afterCursor(afterCursor)
            .size(RemoteIssueService.SIZE_REPOSITORY_PER_PAGE)
            .build()

        val response = apolloClient.query(query)
            .responseFetcher(ApolloResponseFetchers.NETWORK_FIRST)
            .toDeferred()
            .await()

        response.data?.repository()?.let { repository ->
            val issues = mutableListOf<PullRequest>().apply {
                repository.pullRequests().edges()?.mapNotNull { it.node()?.let { pullRequestMapper.map(it) } }?.let {
                    addAll(it)
                }
            }

            val paginatedList = PaginatedList(
                issues,
                repository.pullRequests().pageInfo().hasNextPage(),
                repository.pullRequests().totalCount(),
                repository.pullRequests().edges()?.lastOrNull()?.cursor()
            )

            return success(paginatedList)
        }

        return failure(IllegalStateException()) //TODO MORE INFO
    }
}