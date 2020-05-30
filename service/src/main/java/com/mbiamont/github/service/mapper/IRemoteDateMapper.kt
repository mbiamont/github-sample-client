package com.mbiamont.github.service.mapper

import java.util.*

interface IRemoteDateMapper {

    fun map(date: String): Date?

    fun map(date: Date): String
}