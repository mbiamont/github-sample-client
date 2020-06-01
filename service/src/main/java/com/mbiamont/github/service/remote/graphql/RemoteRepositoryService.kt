package com.mbiamont.github.service.remote.graphql

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.fetcher.ApolloResponseFetchers
import com.mbiamont.github.core.Monad
import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.domain.entity.RepositoryExtract
import com.mbiamont.github.datasource.service.IRemoteRepositoryService
import com.mbiamont.github.domain.entity.RepositoryDetails
import com.mbiamont.github.domain.exception.NetworkException
import com.mbiamont.github.service.graphql.FetchRepositoryDetailsQuery
import com.mbiamont.github.service.graphql.FetchRepositoryPullRequestsQuery
import com.mbiamont.github.service.graphql.FetchUserPublicRepositoriesQuery
import com.mbiamont.github.service.mapper.IRemoteRepositoryMapper
import java.io.IOException
import java.lang.IllegalStateException

class RemoteRepositoryService(
    private val apolloClient: ApolloClient,
    private val remoteRepositoryMapper: IRemoteRepositoryMapper
) : IRemoteRepositoryService {

    override suspend fun getUserPublicRepositories(afterCursor: String?): Monad<PaginatedList<RepositoryExtract>> {
        val query = FetchUserPublicRepositoriesQuery.builder()
            .afterCursor(afterCursor)
            .size(SIZE_REPOSITORY_PER_PAGE)
            .build()

        lateinit var response: Response<FetchUserPublicRepositoriesQuery.Data>
        try {
            response = apolloClient.query(query)
                .responseFetcher(ApolloResponseFetchers.NETWORK_FIRST)
                .toDeferred()
                .await()
        } catch (e: Exception) {
            return failure(NetworkException(e.message))
        }

        response.data?.viewer()?.let {
            val repositories = mutableListOf<RepositoryExtract>().apply {
                it.repositories().edges()?.mapNotNull { it.node()?.let { remoteRepositoryMapper.map(it) } }?.let {
                    addAll(it)
                }
            }

            val paginatedList = PaginatedList(
                repositories,
                it.repositories().pageInfo().hasNextPage(),
                it.repositories().totalCount(),
                it.repositories().edges()?.lastOrNull()?.cursor()
            )

            return success(paginatedList)
        }

        return failure(IllegalStateException()) //TODO MORE INFO
    }

    override suspend fun getRepositoryWithNameAndOwner(name: String, ownerLogin: String): Monad<RepositoryDetails> {
        val query = FetchRepositoryDetailsQuery
            .builder()
            .name(name)
            .ownerLogin(ownerLogin)
            .build()

        lateinit var response: Response<FetchRepositoryDetailsQuery.Data>
        try {
            response = apolloClient.query(query)
                .responseFetcher(ApolloResponseFetchers.NETWORK_FIRST)
                .toDeferred()
                .await()
        } catch (e: Exception) {
            return failure(NetworkException(e.message))
        }

        response.data?.repository()?.let {
            val repo = remoteRepositoryMapper.map(it)

            return success(repo)
        }

        return failure(IllegalStateException()) //TODO MORE INFO
    }

    companion object {
        const val SIZE_REPOSITORY_PER_PAGE = 10
    }
}