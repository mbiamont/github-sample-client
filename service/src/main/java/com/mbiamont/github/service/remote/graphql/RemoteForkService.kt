package com.mbiamont.github.service.remote.graphql

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.mbiamont.github.core.Monad
import com.mbiamont.github.core.PaginatedList
import com.mbiamont.github.core.failure
import com.mbiamont.github.core.success
import com.mbiamont.github.datasource.service.IRemoteForkService
import com.mbiamont.github.domain.entity.Fork
import com.mbiamont.github.domain.exception.NetworkException
import com.mbiamont.github.service.graphql.FetchRepositoryForksQuery
import com.mbiamont.github.service.mapper.IRemoteForkMapper
import java.lang.IllegalStateException

class RemoteForkService(
    private val apolloClient: ApolloClient,
    private val forkMapper: IRemoteForkMapper
) : IRemoteForkService {

    override suspend fun getRepositoryForks(repositoryName: String, ownerLogin: String, afterCursor: String?): Monad<PaginatedList<Fork>> {
        val query = FetchRepositoryForksQuery.builder()
            .name(repositoryName)
            .ownerLogin(ownerLogin)
            .afterCursor(afterCursor)
            .size(RemoteIssueService.SIZE_REPOSITORY_PER_PAGE)
            .build()

        lateinit var response: Response<FetchRepositoryForksQuery.Data>
        try {
            response = apolloClient.query(query).await()
        } catch (e: Exception) {
            return failure(NetworkException(e.message))
        }

        response.data?.repository()?.let { repository ->
            val issues = mutableListOf<Fork>().apply {
                repository.forks().edges()?.mapNotNull { it.node()?.let { forkMapper.map(it) } }?.let {
                    addAll(it)
                }
            }

            val paginatedList = PaginatedList(
                issues,
                repository.forks().pageInfo().hasNextPage(),
                repository.forks().totalCount(),
                repository.forks().edges()?.lastOrNull()?.cursor()
            )

            return success(paginatedList)
        }

        return failure(IllegalStateException()) //TODO MORE INFO
    }
}