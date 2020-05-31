package com.mbiamont.github.repository.forks

import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState

interface IForksView {

    fun displayForkList(forkListViewState: List<ForkViewState>)

    fun displayTimeSerieProgress(progressViewState: ProgressViewState)

    fun displayTimeSerie(timeSerieViewState: TimeSerieViewState)
}