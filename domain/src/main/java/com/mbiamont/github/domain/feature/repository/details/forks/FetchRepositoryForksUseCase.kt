package com.mbiamont.github.domain.feature.repository.details.forks

interface FetchRepositoryForksUseCase {

    suspend fun fetchRepositoryForks(repositoryName: String, ownerLogin: String)
}