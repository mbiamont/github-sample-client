package com.mbiamont.github.repository.list

import com.mbiamont.github.core.BasePresenter
import com.mbiamont.github.domain.entity.RepositoryExtract

interface IRepositoryListPresenter : BasePresenter<IRepositoryListView> {

    fun displayRepositoryExtracts(repositories: List<RepositoryExtract>)

    fun displayFetchRepositoriesError()
}