package com.mbiamont.github.repository.forks

import com.mbiamont.github.core.BasePresenter
import com.mbiamont.github.domain.entity.Fork
import com.mbiamont.github.domain.entity.PullRequest

interface IForksPresenter: BasePresenter<IForksView> {

    fun displayForks(forkList: List<Fork>)

    fun displayTimeSerieProgress(isLoading: Boolean)

    fun displayTimeSerie(forksPerWeek: Array<Int>)

    fun displayFetchForksError()
}