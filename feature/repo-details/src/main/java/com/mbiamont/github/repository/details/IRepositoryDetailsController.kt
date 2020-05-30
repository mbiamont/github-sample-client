package com.mbiamont.github.repository.details

interface IRepositoryDetailsController {

    suspend fun onViewReady(repositoryName: String, ownerLogin: String)
}