package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.User
import com.mbiamont.github.service.FetchRepositoryWithNameAndOwnerQuery
import com.mbiamont.github.service.FetchUserPublicRepositoriesQuery

interface IRemoteUserMapper {

    fun map(user: FetchUserPublicRepositoriesQuery.Owner): User

    fun map(user: FetchRepositoryWithNameAndOwnerQuery.Owner): User
}