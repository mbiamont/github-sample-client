package com.mbiamont.github.domain.entity

data class RepositoryDetails(
    val name: String,
    val owner: User,
    val description: String,
    val starsCount: Int,
    val mainLanguage: Language?,
    val issuesCount: Int,
    val pullRequestsCount: Int,
    val forksCount: Int
)