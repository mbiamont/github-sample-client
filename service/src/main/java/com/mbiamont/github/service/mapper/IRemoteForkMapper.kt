package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.Fork
import com.mbiamont.github.service.graphql.FetchRepositoryForksQuery

interface IRemoteForkMapper {

    fun map(fork: FetchRepositoryForksQuery.Node): Fork
}