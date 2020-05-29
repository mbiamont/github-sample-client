package com.mbiamont.github.main

import com.mbiamont.github.core.onFailure
import com.mbiamont.github.core.onSuccess
import com.mbiamont.github.domain.datasource.IRepositoryDataSource
import com.mbiamont.github.domain.feature.main.FetchUserPublicRepositoriesUseCase
import timber.log.Timber

class MainInteractor(
    private val repositoryDataSource: IRepositoryDataSource
) : FetchUserPublicRepositoriesUseCase {

    override suspend fun fetchUserPublicRepositories() {
        repositoryDataSource.getUserPublicRepositories().onSuccess {
            Timber.i("[MELVIN] REPOSITORY COUNT : ${it.size}")
            it.forEach {
                Timber.i("[MELVIN] ${it.starsCount} ⭐︎ - ${it.name}")
            }
        }.onFailure {
            Timber.e(it)
        }

        repositoryDataSource.getRepositoryWithNameAndOwner("foo", "bar").onSuccess {
            Timber.i("[MELVIN][DETAILS] $it")
        }.onFailure {
            Timber.e(it)
        }
    }
}