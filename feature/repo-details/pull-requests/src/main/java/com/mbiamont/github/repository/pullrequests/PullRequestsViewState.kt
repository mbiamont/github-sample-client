package com.mbiamont.github.repository.pullrequests

data class PullRequestViewState(
    val indexColor: Int,
    val ownerAvatarUrl: String?,
    val ownerLogin: String?,
    val title: String,
    val dateLabel: String,
    val commentCountLabel: String,
    val pullRequestId: String
)