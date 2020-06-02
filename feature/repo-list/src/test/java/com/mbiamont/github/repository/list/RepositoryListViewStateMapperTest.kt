package com.mbiamont.github.repository.list

import com.mbiamont.github.core.android.IColorProvider
import com.mbiamont.github.domain.entity.Language
import com.mbiamont.github.domain.entity.RepositoryExtract
import com.mbiamont.github.domain.entity.User
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class RepositoryListViewStateMapperTest {

    private val colorProvider: IColorProvider = mock()
    private lateinit var viewStateMapper: RepositoryListViewStateMapper

    @Before
    fun setUp() {
        viewStateMapper = RepositoryListViewStateMapper(colorProvider)
    }

    @Test
    fun map() {
        whenever(colorProvider.parse("#123456")).thenReturn(123456)
        val extract = RepositoryExtract("foo.bar", User("foobar", "bar"), 84, Language("baz", "#123456"))
        val expected = RepositoryExtractViewState("foo.bar", "84", LanguageViewState("baz", 123456), "foobar")

        val actual = viewStateMapper.map(extract)

        assertEquals(expected, actual)
    }
}