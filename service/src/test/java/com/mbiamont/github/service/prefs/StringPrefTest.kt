package com.mbiamont.github.service.prefs

import android.content.SharedPreferences
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.lang.RuntimeException

class StringPrefTest {

    private val preferences: SharedPreferences = mock()
    private val editor: SharedPreferences.Editor = mock()

    private lateinit var pref: StringPref

    @Before
    fun setUp() {
        pref = StringPref(key, preferences, defaultValue)

        whenever(preferences.edit()).thenReturn(editor)
        whenever(editor.putString(any(), any())).thenReturn(editor)
        whenever(editor.remove(any())).thenReturn(editor)
    }

    @Test
    fun get_Valid() {
        val expected = "barFOO"
        whenever(preferences.getString(key, defaultValue)).thenReturn(expected)

        val actual = pref.get()

        assertEquals(expected, actual)
    }

    @Test
    fun get_Notfound() {
        whenever(preferences.getString(key, defaultValue)).thenReturn(null)

        val actual = pref.get()

        assertEquals(defaultValue, actual)
    }

    @Test
    fun get_Error() {
        whenever(preferences.getString(key, defaultValue)).thenThrow(RuntimeException("boom"))

        val actual = pref.get()

        assertEquals(defaultValue, actual)
    }

    @Test
    fun set() {
        pref.set("barFOO")

        verify(editor).putString(key, "barFOO")
        verify(editor).apply()
    }

    @Test
    fun isSet() {
        pref.isSet()

        verify(preferences).contains(key)
    }

    @Test
    fun delete() {
        pref.delete()

        verify(editor).remove(key)
        verify(editor).apply()
    }

    private companion object {
        const val key = "foo"
        const val defaultValue = "bar"
    }
}