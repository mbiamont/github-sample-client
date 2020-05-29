package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.User
import com.mbiamont.github.service.FetchRepositoryWithNameAndOwnerQuery
import com.mbiamont.github.service.FetchUserPublicRepositoriesQuery

class RemoteUserMapper: IRemoteUserMapper {

    override fun map(user: FetchUserPublicRepositoriesQuery.Owner): User {
        val avatarUrl = (user.avatarUrl() as? String) ?: ""
        return User(user.login(), avatarUrl)
    }

    override fun map(user: FetchRepositoryWithNameAndOwnerQuery.Owner): User {
        val avatarUrl = (user.avatarUrl() as? String) ?: ""
        return User(user.login(), avatarUrl)
    }
}