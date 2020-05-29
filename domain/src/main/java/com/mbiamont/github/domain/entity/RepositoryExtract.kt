package com.mbiamont.github.domain.entity

data class RepositoryExtract(
    val name: String,
    val owner: User,
    val starsCount: Int,
    val mainLanguage: Language?
)