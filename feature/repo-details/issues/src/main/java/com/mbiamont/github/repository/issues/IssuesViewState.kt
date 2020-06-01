package com.mbiamont.github.repository.issues

data class IssueViewState(
    val indexColor: Int,
    val ownerAvatarUrl: String?,
    val ownerLogin: String?,
    val title: String,
    val dateLabel: String,
    val commentCountLabel: String,
    val issueId: String
)