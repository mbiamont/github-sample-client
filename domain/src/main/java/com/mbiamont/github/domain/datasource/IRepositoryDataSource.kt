package com.mbiamont.github.domain.datasource

import com.mbiamont.github.core.Monad
import com.mbiamont.github.domain.entity.Repository

interface IRepositoryDataSource {

    suspend fun getUserPublicRepositories(): Monad<List<Repository>>
}