package com.mbiamont.github.repository.forks

import com.mbiamont.github.domain.feature.repository.details.forks.FetchRepositoryForksUseCase

class ForksInteractor(
    private val presenter: IForksPresenter
) : FetchRepositoryForksUseCase {

    override suspend fun fetchRepositoryForks(repositoryName: String, ownerLogin: String) {
        //TODO
    }

}