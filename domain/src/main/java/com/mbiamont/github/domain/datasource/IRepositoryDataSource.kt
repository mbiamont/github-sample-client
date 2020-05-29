package com.mbiamont.github.domain.datasource

import com.mbiamont.github.core.Monad
import com.mbiamont.github.domain.entity.RepositoryDetails
import com.mbiamont.github.domain.entity.RepositoryExtract

interface IRepositoryDataSource {

    suspend fun getUserPublicRepositories(): Monad<List<RepositoryExtract>>

    suspend fun getRepositoryWithNameAndOwner(name: String, ownerLogin: String): Monad<RepositoryDetails>
}