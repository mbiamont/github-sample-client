package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.User
import com.mbiamont.github.service.graphql.*

interface IRemoteUserMapper {

    fun map(user: FetchUserPublicRepositoriesQuery.Owner): User

    fun map(user: FetchRepositoryDetailsQuery.Owner): User

    fun map(user: FetchRepositoryIssuesQuery.Author?): User?

    fun map(user: FetchRepositoryForksQuery.Owner): User

    fun map(user: FetchRepositoryPullRequestsQuery.Author?): User?
}