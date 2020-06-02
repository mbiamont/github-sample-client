package com.mbiamont.github.core

data class PaginatedList<T>(
    val values: MutableList<T>,
    val hasNext: Boolean,
    val totalCount: Int,
    val lastItemCursor: String?
) {
    operator fun plus(paginatedList: PaginatedList<T>) = PaginatedList(
        values.apply { addAll(paginatedList.values) },
        paginatedList.hasNext,
        paginatedList.totalCount,
        paginatedList.lastItemCursor
    )
}

fun <T> emptyPaginatedList(hasNext: Boolean = true) = PaginatedList<T>(mutableListOf(), hasNext, 0, null)