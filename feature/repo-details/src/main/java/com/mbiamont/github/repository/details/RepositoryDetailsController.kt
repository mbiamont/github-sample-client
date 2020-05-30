package com.mbiamont.github.repository.details

import com.mbiamont.github.domain.feature.repository.details.FetchRepositoryDetailsUseCase

class RepositoryDetailsController(
    private val fetchRepositoryDetailsUseCase: FetchRepositoryDetailsUseCase
) : IRepositoryDetailsController {

    override suspend fun onViewReady(repositoryName: String, ownerLogin: String) =
        fetchRepositoryDetailsUseCase.fetchRepositoryDetails(repositoryName, ownerLogin)
}