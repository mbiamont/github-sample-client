package com.mbiamont.github.repository.forks

data class ForksViewState(
    val issues: List<ForkViewState>,
    val timeSerie: ForkTimeSerieViewState
)

data class ForkTimeSerieViewState(
    val downloaded: Int,
    val total: Int,
    val progressLabel: String,
    val dataset: Array<Int>?
)

data class ForkViewState(
    val ownerAvatarUrl: String?,
    val ownerLogin: String?,
    val dateLabel: String
)