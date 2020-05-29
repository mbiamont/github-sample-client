package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.RepositoryDetails
import com.mbiamont.github.domain.entity.RepositoryExtract
import com.mbiamont.github.service.graphql.FetchRepositoryDetailsQuery
import com.mbiamont.github.service.graphql.FetchUserPublicRepositoriesQuery

interface IRemoteRepositoryMapper {

    fun map(repo: FetchUserPublicRepositoriesQuery.Node): RepositoryExtract

    fun map(repo: FetchRepositoryDetailsQuery.Repository): RepositoryDetails
}