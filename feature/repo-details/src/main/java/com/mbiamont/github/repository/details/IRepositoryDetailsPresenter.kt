package com.mbiamont.github.repository.details

import com.mbiamont.github.core.BasePresenter
import com.mbiamont.github.domain.entity.RepositoryDetails

interface IRepositoryDetailsPresenter: BasePresenter<IRepositoryDetailsView> {

    fun displayRepositoryDetails(repositoryDetails: RepositoryDetails)
}