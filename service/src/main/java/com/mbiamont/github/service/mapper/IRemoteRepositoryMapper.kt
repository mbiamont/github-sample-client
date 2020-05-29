package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.Repository
import com.mbiamont.github.service.FetchUserRepositoriesQuery

interface IRemoteRepositoryMapper {

    fun map(node: FetchUserRepositoriesQuery.Node): Repository?
}