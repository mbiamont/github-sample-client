package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.Language
import com.mbiamont.github.service.graphql.FetchRepositoryDetailsQuery
import com.mbiamont.github.service.graphql.FetchUserPublicRepositoriesQuery

class RemoteLanguageMapper : IRemoteLanguageMapper {

    override fun map(node: FetchUserPublicRepositoriesQuery.Node1?) = node?.let { Language(it.name(), it.color()) }

    override fun map(node: FetchRepositoryDetailsQuery.Node?) = node?.let { Language(it.name(), it.color()) }
}