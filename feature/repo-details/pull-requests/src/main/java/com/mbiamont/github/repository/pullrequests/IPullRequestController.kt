package com.mbiamont.github.repository.pullrequests

interface IPullRequestController {

    suspend fun onViewReady(repositoryName: String, ownerLogin: String)
}