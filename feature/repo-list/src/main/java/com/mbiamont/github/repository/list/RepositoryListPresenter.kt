package com.mbiamont.github.repository.list

import com.mbiamont.github.domain.entity.RepositoryExtract

class RepositoryListPresenter(
    private val viewStateMapper: IRepositoryListViewStateMapper
) : IRepositoryListPresenter {

    override var view: IRepositoryListView? = null

    override fun displayRepositoryExtracts(repositories: List<RepositoryExtract>) {
        view?.displayRepositoryExtractList(repositories.map { viewStateMapper.map(it) })
    }

    override fun displayFetchRepositoryError() {
        view?.displayFetchRepositoryError(R.string.errorsFetchRepository)
    }
}