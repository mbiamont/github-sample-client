package com.mbiamont.github.design.extensions

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InfiniteScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val onEndReached: () -> Unit
) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var loading = true
    private var visibleThreshold = 10
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if (loading && (totalItemCount > previousTotal)) {
                loading = false
                previousTotal = totalItemCount
            }

            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                // End has been reached
                onEndReached()
                loading = true
            }
        }
    }

    fun onError() {
        loading = false
    }

    fun reset() {
        previousTotal = 0
        loading = true
        visibleThreshold = 10
        firstVisibleItem = 0
        visibleItemCount = 0
        totalItemCount = 0
    }
}

fun createInfiniteScrollListener(layoutManager: LinearLayoutManager, onEndReached: () -> Unit) =
    InfiniteScrollListener(layoutManager, onEndReached)


fun <T> calculateDiff(oldDataset: List<T>, newDataset: List<T>, comparator: (T, T) -> Boolean) =
    DiffUtil.calculateDiff(object : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            comparator(oldDataset[oldItemPosition], newDataset[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldDataset[oldItemPosition] == newDataset[newItemPosition]

        override fun getOldListSize() = oldDataset.size

        override fun getNewListSize() = newDataset.size
    })