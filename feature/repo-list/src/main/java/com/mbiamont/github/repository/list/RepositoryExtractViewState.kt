package com.mbiamont.github.repository.list

data class RepositoryExtractViewState(
    val name: String,
    val starsCount: String,
    val languageViewState: LanguageViewState?,
    val ownerLogin: String
)

data class LanguageViewState(
    val language: String,
    val languageColor: Int
)