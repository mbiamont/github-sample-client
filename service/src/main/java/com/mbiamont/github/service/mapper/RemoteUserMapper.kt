package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.User
import com.mbiamont.github.service.graphql.FetchRepositoryDetailsQuery
import com.mbiamont.github.service.graphql.FetchUserPublicRepositoriesQuery

class RemoteUserMapper: IRemoteUserMapper {

    override fun map(user: FetchUserPublicRepositoriesQuery.Owner): User {
        val avatarUrl = (user.avatarUrl() as? String) ?: ""
        return User(user.login(), avatarUrl)
    }

    override fun map(user: FetchRepositoryDetailsQuery.Owner): User {
        val avatarUrl = (user.avatarUrl() as? String) ?: ""
        return User(user.login(), avatarUrl)
    }
}