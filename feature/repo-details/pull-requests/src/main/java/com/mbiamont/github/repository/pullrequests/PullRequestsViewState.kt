package com.mbiamont.github.repository.pullrequests

data class PullRequestTimeSerieViewState(
    val downloaded: Int,
    val total: Int,
    val progressLabel: String,
    val dataset: Array<Int>?
)

data class PullRequestViewState(
    val indexColor: Int,
    val ownerAvatarUrl: String?,
    val ownerLogin: String?,
    val title: String,
    val dateLabel: String,
    val commentCountLabel: String
)