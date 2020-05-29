package com.mbiamont.github.domain.repository

interface IConfigRepository {

    val githubClientId: String

    val githubOAuthScopes: Array<String>
}