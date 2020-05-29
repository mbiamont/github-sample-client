package com.mbiamont.github.service.mapper

import java.text.SimpleDateFormat

class RemoteDateMapper : IRemoteDateMapper {

    override fun map(date: String) = dateFormat.parse(date)

    companion object {
        const val TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val dateFormat = SimpleDateFormat(TIME_FORMAT)
    }
}