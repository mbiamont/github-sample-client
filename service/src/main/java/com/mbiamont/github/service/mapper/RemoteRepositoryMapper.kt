package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.Repository
import com.mbiamont.github.service.FetchUserRepositoriesQuery

class RemoteRepositoryMapper: IRemoteRepositoryMapper {

    override fun map(node: FetchUserRepositoriesQuery.Node) = (node.projectsUrl() as? String)?.let { Repository(it) }
}