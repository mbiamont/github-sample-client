package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.User
import com.mbiamont.github.service.graphql.FetchRepositoryDetailsQuery
import com.mbiamont.github.service.graphql.FetchUserPublicRepositoriesQuery

interface IRemoteUserMapper {

    fun map(user: FetchUserPublicRepositoriesQuery.Owner): User

    fun map(user: FetchRepositoryDetailsQuery.Owner): User
}