package com.mbiamont.github.datasource

import com.mbiamont.github.datasource.service.IRemoteForkService
import com.mbiamont.github.domain.datasource.IForkDataSource

class ForkDataSource(
    private val remoteForkService: IRemoteForkService
) : IForkDataSource {

    override suspend fun getRepositoryForks(repositoryName: String, ownerLogin: String, afterCursor: String?) =
        remoteForkService.getRepositoryForks(repositoryName, ownerLogin, afterCursor)
}