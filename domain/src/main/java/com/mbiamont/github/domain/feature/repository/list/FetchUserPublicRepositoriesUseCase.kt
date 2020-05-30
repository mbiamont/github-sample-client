package com.mbiamont.github.domain.feature.repository.list

interface FetchUserPublicRepositoriesUseCase {

    suspend fun fetchUserPublicRepositories()
}