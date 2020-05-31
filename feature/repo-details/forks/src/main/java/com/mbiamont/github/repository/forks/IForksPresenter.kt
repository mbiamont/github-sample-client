package com.mbiamont.github.repository.forks

import com.mbiamont.github.core.BasePresenter
import com.mbiamont.github.domain.entity.Fork

interface IForksPresenter: BasePresenter<IForksView> {

    fun displayForks(forksList: List<Fork>, count: Int, totalCount: Int, forksPerWeek: Array<Int>?)
}