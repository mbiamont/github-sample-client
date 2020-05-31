package com.mbiamont.github.repository.forks

import com.mbiamont.github.domain.entity.Fork

class ForksPresenter(
    private val viewStateMapper: IForksViewStateMapper
) : IForksPresenter {

    override var view: IForksView? = null

    override fun displayForks(forkList: List<Fork>) {
        view?.displayForkList(forkList.map { viewStateMapper.map(it) })
    }

    override fun displayTimeSerieProgress(isLoading: Boolean) {
        view?.displayTimeSerieProgress(viewStateMapper.map(isLoading))
    }

    override fun displayTimeSerie(forksPerWeek: Array<Int>) {
        view?.displayTimeSerie(viewStateMapper.map(forksPerWeek))
    }
}