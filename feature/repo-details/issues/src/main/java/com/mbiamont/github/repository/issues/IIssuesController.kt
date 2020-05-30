package com.mbiamont.github.repository.issues

interface IIssuesController {

    suspend fun onViewReady(repositoryName: String, ownerLogin: String)
}