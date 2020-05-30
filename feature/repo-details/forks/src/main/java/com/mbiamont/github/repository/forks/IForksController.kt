package com.mbiamont.github.repository.forks

interface IForksController {

    suspend fun onViewReady(repositoryName: String, ownerLogin: String)
}