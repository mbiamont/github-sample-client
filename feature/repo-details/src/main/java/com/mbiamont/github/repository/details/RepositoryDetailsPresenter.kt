package com.mbiamont.github.repository.details

import com.mbiamont.github.domain.entity.RepositoryDetails

class RepositoryDetailsPresenter(
    private val viewStateMapper: IRepositoryDetailsViewStateMapper
) : IRepositoryDetailsPresenter {

    override var view: IRepositoryDetailsView? = null

    override fun displayRepositoryDetails(repositoryDetails: RepositoryDetails) {
        view?.displayRepositoryDetails(viewStateMapper.map(repositoryDetails))
    }

    override fun displayFetchRepositoryDetailsError() {
        view?.displayErrorMessage(R.string.errorsFetchRepositoryDetails)
    }

}