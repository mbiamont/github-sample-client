package com.mbiamont.github.repository.service

interface IConfigService {

    val githubClientId: String

    val githubSecret: String

    val githubOAuthScopes: Array<String>
}