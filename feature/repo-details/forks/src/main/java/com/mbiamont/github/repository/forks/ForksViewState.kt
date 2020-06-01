package com.mbiamont.github.repository.forks

data class ForkViewState(
    val ownerAvatarUrl: String?,
    val ownerLogin: String?,
    val dateLabel: String,
    val forkId: String
)