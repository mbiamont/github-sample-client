package com.mbiamont.github.datasource

import com.mbiamont.github.datasource.service.IConfigService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

class ConfigDataSourceTest {

    private lateinit var dataSource: ConfigDataSource
    private val configService: IConfigService = mock()

    @Before
    fun setUp() {
        dataSource = ConfigDataSource(configService)
    }

    @Test
    fun getGithubClientId() {
        dataSource.githubClientId

        verify(configService).githubClientId
    }

    @Test
    fun getGithubOAuthScopes() {
        dataSource.githubOAuthScopes

        verify(configService).githubOAuthScopes
    }
}