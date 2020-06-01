package com.mbiamont.github.repository.pullrequests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbiamont.github.design.card.PullRequestCard
import com.mbiamont.github.design.extensions.calculateDiff

class PullRequestsAdapter : RecyclerView.Adapter<PullRequestsAdapter.ItemViewHolder>() {

    private val itemDataset = mutableListOf<PullRequestViewState>()

    fun updateViewState(issues: List<PullRequestViewState>) {
        val diff = calculateDiff(itemDataset, issues) { old, new ->
            old.pullRequestId == new.pullRequestId
        }
        itemDataset.clear()
        itemDataset.addAll(issues)

        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pull_request, parent, false))

    override fun getItemCount() = itemDataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(itemDataset[position])

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(viewState: PullRequestViewState) {
            with(itemView as PullRequestCard) {
                author = viewState.ownerLogin ?: ""
                title = viewState.title
                dateLabel = viewState.dateLabel
                commentsCountLabel = viewState.commentCountLabel
                setIndexColor(viewState.indexColor)
                setAvatarUrl(viewState.ownerAvatarUrl ?: "")
            }
        }
    }
}