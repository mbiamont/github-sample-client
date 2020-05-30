package com.mbiamont.github.domain.feature.repository.details.pullrequests

interface FetchRepositoryPullRequestsUseCase {

    suspend fun fetchRepositoryPullRequests(repositoryName: String, ownerLogin: String)
}