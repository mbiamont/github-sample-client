package com.mbiamont.github.domain.entity

import java.util.*

data class Fork(
    val createdAt: Calendar,
    val owner: User?
)