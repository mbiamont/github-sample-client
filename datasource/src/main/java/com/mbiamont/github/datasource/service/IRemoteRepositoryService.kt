package com.mbiamont.github.datasource.service

import com.mbiamont.github.core.Monad
import com.mbiamont.github.domain.entity.Repository

interface IRemoteRepositoryService {

    suspend fun getUserPublicRepositories(): Monad<List<Repository>>
}