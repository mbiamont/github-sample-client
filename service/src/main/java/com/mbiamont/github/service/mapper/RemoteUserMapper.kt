package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.User
import com.mbiamont.github.service.graphql.FetchRepositoryDetailsQuery
import com.mbiamont.github.service.graphql.FetchRepositoryIssuesQuery
import com.mbiamont.github.service.graphql.FetchUserPublicRepositoriesQuery

class RemoteUserMapper : IRemoteUserMapper {

    override fun map(user: FetchUserPublicRepositoriesQuery.Owner) = User(
        login = user.login(),
        avatarUrl = (user.avatarUrl() as? String)
    )

    override fun map(user: FetchRepositoryDetailsQuery.Owner) = User(
        login = user.login(),
        avatarUrl = (user.avatarUrl() as? String)
    )

    override fun map(user: FetchRepositoryIssuesQuery.Author?) = user?.let {
        User(
            login = user.login(),
            avatarUrl = (user.avatarUrl() as? String)
        )
    }
}