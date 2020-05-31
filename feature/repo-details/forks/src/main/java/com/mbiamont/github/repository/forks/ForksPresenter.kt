package com.mbiamont.github.repository.forks

import com.mbiamont.github.domain.entity.Fork

class ForksPresenter(
    private val viewStateMapper: IForksViewStateMapper
) : IForksPresenter {

    override var view: IForksView? = null

    override fun displayForks(forksList: List<Fork>, count: Int, totalCount: Int, forksPerWeek: Array<Int>?) {
        view?.displayForkList(
            ForksViewState(
                forksList.map { viewStateMapper.map(it) },
                viewStateMapper.map(count, totalCount, forksPerWeek)
            )
        )
    }
}