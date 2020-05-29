package com.mbiamont.github.main

import com.mbiamont.github.domain.entity.RepositoryExtract

class MainPresenter(
    private val viewStateMapper: IMainViewStateMapper
) : IMainPresenter {

    override var view: IMainView? = null

    override fun displayRepositoryExtracts(repositories: List<RepositoryExtract>) {
        view?.displayRepositoryExtractList(repositories.map { viewStateMapper.map(it) })
    }

    override fun displayFetchRepositoryError() {
        view?.displayFetchRepositoryError(R.string.errorsFetchRepository)
    }
}