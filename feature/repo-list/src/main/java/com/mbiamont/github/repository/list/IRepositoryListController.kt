package com.mbiamont.github.repository.list

interface IRepositoryListController {

    suspend fun onViewReady()

    suspend fun onRepositoriesScrolled()

    suspend fun onRepositoryClicked(repositoryName: String, ownerLogin: String)
}