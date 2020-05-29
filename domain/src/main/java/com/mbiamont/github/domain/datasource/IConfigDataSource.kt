package com.mbiamont.github.domain.datasource

interface IConfigDataSource {

    val githubClientId: String

    val githubOAuthScopes: Array<String>
}