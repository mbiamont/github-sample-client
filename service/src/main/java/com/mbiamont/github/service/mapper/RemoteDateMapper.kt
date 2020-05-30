package com.mbiamont.github.service.mapper

import java.text.SimpleDateFormat
import java.util.*

class RemoteDateMapper : IRemoteDateMapper {

    override fun map(date: String) = dateFormat.parse(date)

    override fun map(date: Date) = dateFormat.format(date)

    companion object {
        const val TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val dateFormat = SimpleDateFormat(TIME_FORMAT)
    }
}