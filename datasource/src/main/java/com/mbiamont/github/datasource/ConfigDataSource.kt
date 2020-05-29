package com.mbiamont.github.datasource

import com.mbiamont.github.domain.datasource.IConfigDataSource
import com.mbiamont.github.datasource.service.IConfigService

class ConfigDataSource(
    private val configService: IConfigService
) : IConfigDataSource {

    override val githubClientId: String
        get() = configService.githubClientId

    override val githubOAuthScopes: Array<String>
        get() = configService.githubOAuthScopes
}