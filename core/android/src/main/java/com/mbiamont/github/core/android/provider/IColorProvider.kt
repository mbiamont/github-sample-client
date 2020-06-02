package com.mbiamont.github.core.android.provider

interface IColorProvider {

    fun parse(color: String?): Int
}