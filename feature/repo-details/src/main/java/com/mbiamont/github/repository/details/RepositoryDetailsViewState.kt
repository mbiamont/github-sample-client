package com.mbiamont.github.repository.details

data class RepositoryDetailsViewState(
    val ownerName: String,
    val ownerAvatarUrl: String?,
    val repositoryName: String,
    val repositoryDescription: String,
    val mainLanguage: MainLanguageViewState?,
    val starCountLabel: String,
    val pullRequestCountLabel: String,
    val forkCountLabel: String
)

data class MainLanguageViewState(
    val label: String,
    val color: Int
)