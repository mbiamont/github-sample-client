package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.Language
import com.mbiamont.github.service.graphql.FetchRepositoryDetailsQuery
import com.mbiamont.github.service.graphql.FetchUserPublicRepositoriesQuery

interface IRemoteLanguageMapper {

    fun map(node: FetchUserPublicRepositoriesQuery.Node1?): Language?

    fun map(node: FetchRepositoryDetailsQuery.Node?): Language?
}