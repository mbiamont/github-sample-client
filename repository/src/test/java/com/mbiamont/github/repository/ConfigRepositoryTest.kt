package com.mbiamont.github.repository

import com.mbiamont.github.repository.service.IConfigService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class ConfigRepositoryTest {

    private lateinit var repository: ConfigRepository
    private val configService: IConfigService = mock()

    @Before
    fun setUp() {
        repository = ConfigRepository(configService)
    }

    @Test
    fun getGithubClientId() {
        repository.githubClientId

        verify(configService).githubClientId
    }

    @Test
    fun getGithubOAuthScopes() {
        repository.githubOAuthScopes

        verify(configService).githubOAuthScopes
    }
}