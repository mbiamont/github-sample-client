package com.mbiamont.github.service.mapper

import java.util.*

interface IRemoteDateMapper {

    fun map(date: String): Date?

    fun mapToCalendar(date: String): Calendar?

    fun map(date: Date): String
}