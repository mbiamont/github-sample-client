package com.mbiamont.github.domain.feature.repository.details.issues

interface FetchRepositoryIssuesUseCase {

    suspend fun fetchRepositoryIssues(repositoryName: String, ownerLogin: String)
}