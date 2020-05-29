package com.mbiamont.github.main

data class RepositoryExtractViewState(
    val name: String,
    val starsCount: String,
    val languageViewState: LanguageViewState?
)

data class LanguageViewState(
    val language: String,
    val languageColor: Int
)