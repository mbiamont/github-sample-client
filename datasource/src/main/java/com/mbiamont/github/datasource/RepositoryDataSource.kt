package com.mbiamont.github.datasource

import com.mbiamont.github.datasource.service.IRemoteRepositoryService
import com.mbiamont.github.domain.datasource.IRepositoryDataSource

class RepositoryDataSource(
    private val remoteRepositoryService: IRemoteRepositoryService
) : IRepositoryDataSource {

    override suspend fun getUserPublicRepositories() = remoteRepositoryService.getUserPublicRepositories()
}