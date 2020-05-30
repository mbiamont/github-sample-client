package com.mbiamont.github.repository.list

interface IRepositoryListView {

    fun displayRepositoryExtractList(repositories: List<RepositoryExtractViewState>)

    fun displayFetchRepositoryError(messageId: Int)
}