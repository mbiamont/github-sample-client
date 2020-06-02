package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.Language
import com.mbiamont.github.service.graphql.FetchRepositoryDetailsQuery
import com.mbiamont.github.service.graphql.FetchUserPublicRepositoriesQuery
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class RemoteLanguageMapperTest {

    private lateinit var mapper: RemoteLanguageMapper

    @Before
    fun setUp() {
        mapper = RemoteLanguageMapper()
    }

    @Test
    fun map() {
        val actual = mapper.map(data1)

        assertEquals(expected, actual)
    }

    @Test
    fun mapBis() {
        val actual = mapper.map(data2)

        assertEquals(expected, actual)
    }

    private companion object {
        const val languageName = "foo"
        const val languageColor = "#123123"

        val expected = Language(languageName, languageColor)

        val data1 = FetchUserPublicRepositoriesQuery.Node1("language", languageColor, languageName)

        val data2 = FetchRepositoryDetailsQuery.Node("language", languageColor, languageName)
    }
}