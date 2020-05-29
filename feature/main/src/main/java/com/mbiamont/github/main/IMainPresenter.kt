package com.mbiamont.github.main

import com.mbiamont.github.core.BasePresenter
import com.mbiamont.github.domain.entity.RepositoryExtract

interface IMainPresenter : BasePresenter<IMainView> {

    fun displayRepositoryExtracts(repositories: List<RepositoryExtract>)

    fun displayFetchRepositoryError()
}