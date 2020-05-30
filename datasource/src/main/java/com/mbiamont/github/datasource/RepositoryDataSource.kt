package com.mbiamont.github.datasource

import com.mbiamont.github.datasource.service.IRemoteRepositoryService
import com.mbiamont.github.domain.datasource.IRepositoryDataSource

class RepositoryDataSource(
    private val remoteRepositoryService: IRemoteRepositoryService
) : IRepositoryDataSource {

    override suspend fun getUserPublicRepositories(afterCursor: String?) = remoteRepositoryService.getUserPublicRepositories(afterCursor)

    override suspend fun getRepositoryWithNameAndOwner(name: String, ownerLogin: String) =
        remoteRepositoryService.getRepositoryWithNameAndOwner(name, ownerLogin)
}