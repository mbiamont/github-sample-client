package com.mbiamont.github.repository

import com.mbiamont.github.domain.repository.IConfigRepository
import com.mbiamont.github.repository.service.IConfigService

class ConfigRepository(
    private val configService: IConfigService
) : IConfigRepository {

    override val githubClientId: String
        get() = configService.githubClientId

    override val githubOAuthScopes: Array<String>
        get() = configService.githubOAuthScopes
}