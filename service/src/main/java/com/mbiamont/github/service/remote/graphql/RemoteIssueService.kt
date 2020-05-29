package com.mbiamont.github.service.remote.graphql

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.fetcher.ApolloResponseFetchers
import com.mbiamont.github.core.Monad
import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.datasource.service.IRemoteIssueService
import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.service.graphql.FetchRepositoryIssuesQuery
import com.mbiamont.github.service.mapper.IRemoteIssueMapper
import java.lang.IllegalStateException

class RemoteIssueService(
    private val apolloClient: ApolloClient,
    private val fetchRepositoryIssuesQuery: FetchRepositoryIssuesQuery,
    private val remoteIssueMapper: IRemoteIssueMapper
) : IRemoteIssueService {

    override suspend fun getRepositoryIssues(repositoryName: String, ownerLogin: String): Monad<List<Issue>> {
        val response = apolloClient.query(fetchRepositoryIssuesQuery)
            .responseFetcher(ApolloResponseFetchers.NETWORK_FIRST)
            .toDeferred()
            .await()

        response.data?.repository()?.let { repository ->
            val issues = repository.issues().nodes()?.map { remoteIssueMapper.map(it) } ?: emptyList()
            return success(issues)
        }

        return failure(IllegalStateException()) //TODO MORE INFO
    }
}