package com.mbiamont.github.repository.issues

data class IssuesViewState(
    val issues: List<IssueViewState>,
    val timeSerie: IssueTimeSerieViewState
)

data class IssueTimeSerieViewState(
    val downloaded: Int,
    val total: Int,
    val progressLabel: String,
    val dataset: Array<Int>?
)

data class IssueViewState(
    val indexColor: Int,
    val ownerAvatarUrl: String?,
    val ownerLogin: String?,
    val title: String,
    val dateLabel: String,
    val commentCountLabel: String
)