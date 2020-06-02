package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.Language
import com.mbiamont.github.domain.entity.RepositoryDetails
import com.mbiamont.github.domain.entity.RepositoryExtract
import com.mbiamont.github.domain.entity.User
import com.mbiamont.github.service.graphql.FetchRepositoryDetailsQuery
import com.mbiamont.github.service.graphql.FetchUserPublicRepositoriesQuery
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class RemoteRepositoryMapperTest {

    private val userMapper: IRemoteUserMapper = mock()
    private val languageMapper: IRemoteLanguageMapper = mock()

    private lateinit var mapper: RemoteRepositoryMapper

    @Before
    fun setUp() {
        mapper = RemoteRepositoryMapper(userMapper, languageMapper)
    }

    @Test
    fun mapExtract() {
        val ownerData = FetchUserPublicRepositoriesQuery.Owner("user", "foo", "bar")
        val starsData = FetchUserPublicRepositoriesQuery.Stargazers("stars", starsCount)
        val languageData = FetchUserPublicRepositoriesQuery.Node1("language", "#123456", "foobar")
        val languagesData = FetchUserPublicRepositoriesQuery.Languages("language", listOf(languageData))

        val data = FetchUserPublicRepositoriesQuery.Node("repo", repoName, ownerData, starsData, languagesData)

        whenever(userMapper.map(ownerData)).thenReturn(owner)
        whenever(languageMapper.map(languageData)).thenReturn(mainLanguage)


        val expected = RepositoryExtract(
            name = repoName,
            owner = owner,
            mainLanguage = mainLanguage,
            starsCount = starsCount
        )

        val actual = mapper.map(data)

        assertEquals(expected, actual)
    }

    @Test
    fun mapDetails() {
        val ownerData = FetchRepositoryDetailsQuery.Owner("user", "foo", "bar")
        val starsData = FetchRepositoryDetailsQuery.Stargazers("stars", starsCount)
        val languageData = FetchRepositoryDetailsQuery.Node("language", "#123456", "foobar")
        val languagesData = FetchRepositoryDetailsQuery.Languages("languages", listOf(languageData))
        val issuesData = FetchRepositoryDetailsQuery.Issues("issues", issuesCount)
        val prsData = FetchRepositoryDetailsQuery.PullRequests("prs", pullRequestsCount)
        val forks = FetchRepositoryDetailsQuery.Forks("forks", forksCount)

        val data = FetchRepositoryDetailsQuery.Repository(
            "repo",
            description,
            ownerData,
            repoName,
            starsData,
            languagesData,
            issuesData,
            prsData,
            forks
        )

        whenever(userMapper.map(ownerData)).thenReturn(owner)
        whenever(languageMapper.map(languageData)).thenReturn(mainLanguage)

        val expected = RepositoryDetails(
            name = repoName,
            owner = owner,
            description = description,
            mainLanguage = mainLanguage,
            starsCount = starsCount,
            issuesCount = issuesCount,
            pullRequestsCount = pullRequestsCount,
            forksCount = forksCount
        )

        val actual = mapper.map(data)

        assertEquals(expected, actual)
    }

    private companion object {
        const val repoName = "foo"
        val owner = User("foo", "bar")
        val mainLanguage = Language("barfoo", "#123321")
        const val starsCount = 678
        const val description = "blablabla"
        const val issuesCount = 93
        const val pullRequestsCount = 1038
        const val forksCount = 75
    }
}