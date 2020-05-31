package com.mbiamont.github.repository.forks

import com.mbiamont.github.domain.entity.Fork

interface IForksViewStateMapper {

    fun map(count: Int, totalCount: Int, forksPerWeek: Array<Int>?): ForkTimeSerieViewState

    fun map(fork: Fork): ForkViewState
}