package com.mbiamont.github.core.android.viewstate

data class TimeSerieViewState(
    val dataset: Array<Int>
)

data class ProgressViewState(
    val isLoading: Boolean
)