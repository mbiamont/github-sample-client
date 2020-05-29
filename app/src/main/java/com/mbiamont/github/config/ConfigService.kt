package com.mbiamont.github.config

import com.mbiamont.github.BuildConfig
import com.mbiamont.github.repository.service.IConfigService

class ConfigService: IConfigService {

    override val githubClientId: String
        get() = BuildConfig.GITHUB_CLIENT_ID

    override val githubSecret: String
        get() = BuildConfig.GITHUB_SECRET

    override val githubOAuthScopes: Array<String>
        get() = emptyArray()
}