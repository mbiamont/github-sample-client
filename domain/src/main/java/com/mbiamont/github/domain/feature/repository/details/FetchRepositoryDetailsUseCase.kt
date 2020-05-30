package com.mbiamont.github.domain.feature.repository.details

interface FetchRepositoryDetailsUseCase {

    suspend fun fetchRepositoryDetails(repositoryName: String, ownerLogin: String)
}