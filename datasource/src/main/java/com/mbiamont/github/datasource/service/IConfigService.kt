package com.mbiamont.github.datasource.service

interface IConfigService {

    val githubClientId: String

    val githubSecret: String

    val githubOAuthScopes: Array<String>
}