package com.mbiamont.github.domain.entity

import java.util.*

data class Issue(
    val title: String,
    val state: State,
    val createdAt: Calendar,
    val author: User?,
    val commentsCount: Int
) {
    enum class State {
        OPEN,
        CLOSED,
        UNKNOWN
    }
}
