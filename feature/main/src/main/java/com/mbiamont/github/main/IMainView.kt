package com.mbiamont.github.main

interface IMainView {

    fun displayRepositoryExtractList(repositories: List<RepositoryExtractViewState>)

    fun displayFetchRepositoryError(messageId: Int)
}