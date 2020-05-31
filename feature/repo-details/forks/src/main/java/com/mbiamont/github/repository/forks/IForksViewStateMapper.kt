package com.mbiamont.github.repository.forks

import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState
import com.mbiamont.github.domain.entity.Fork

interface IForksViewStateMapper {

    fun map(isLoading: Boolean): ProgressViewState

    fun map(forksPerWeek: Array<Int>): TimeSerieViewState

    fun map(fork: Fork): ForkViewState
}