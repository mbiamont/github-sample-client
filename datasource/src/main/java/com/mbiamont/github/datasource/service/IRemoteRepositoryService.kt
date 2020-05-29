package com.mbiamont.github.datasource.service

import com.mbiamont.github.core.Monad
import com.mbiamont.github.domain.entity.RepositoryDetails
import com.mbiamont.github.domain.entity.RepositoryExtract

interface IRemoteRepositoryService {

    suspend fun getUserPublicRepositories(): Monad<List<RepositoryExtract>>

    suspend fun getRepositoryWithNameAndOwner(name: String, ownerLogin: String): Monad<RepositoryDetails>
}