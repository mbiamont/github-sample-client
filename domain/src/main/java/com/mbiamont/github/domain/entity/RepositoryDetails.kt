package com.mbiamont.github.domain.entity

data class RepositoryDetails(
    val name: String,
    val owner: User,
    val starsCount: Int,
    val issuesCount: Int,
    val pullRequestsCount: Int,
    val forksCount: Int
)