package com.mbiamont.github.service.remote.graphql

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.fetcher.ApolloResponseFetchers
import com.mbiamont.github.core.Monad
import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.domain.entity.Repository
import com.mbiamont.github.datasource.service.IRemoteRepositoryService
import com.mbiamont.github.service.FetchUserRepositoriesQuery
import com.mbiamont.github.service.mapper.IRemoteRepositoryMapper
import java.lang.IllegalStateException

class RemoteRepositoryService(
    private val apolloClient: ApolloClient,
    private val fetchUserRepositoriesQuery: FetchUserRepositoriesQuery,
    private val remoteRepositoryMapper: IRemoteRepositoryMapper
) : IRemoteRepositoryService {

    override suspend fun getUserPublicRepositories(): Monad<List<Repository>> {
        val response = apolloClient.query(fetchUserRepositoriesQuery)
            .responseFetcher(ApolloResponseFetchers.NETWORK_FIRST)
            .toDeferred()
            .await()

        response.data?.viewer()?.let {
            val repositories = it.repositories().nodes()?.mapNotNull {remoteRepositoryMapper.map(it) } ?: emptyList()

            return success(repositories)
        }

        return failure(IllegalStateException()) //TODO MORE INFO
    }
}