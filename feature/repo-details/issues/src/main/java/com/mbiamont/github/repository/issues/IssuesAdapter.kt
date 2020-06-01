package com.mbiamont.github.repository.issues

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbiamont.github.design.card.IssueCard
import com.mbiamont.github.design.extensions.calculateDiff

class IssuesAdapter : RecyclerView.Adapter<IssuesAdapter.ItemViewHolder>() {

    private val itemDataset = mutableListOf<IssueViewState>()

    fun updateViewState(issues: List<IssueViewState>) {
        val diff = calculateDiff(itemDataset, issues) { old, new ->
            old.issueId == new.issueId
        }
        itemDataset.clear()
        itemDataset.addAll(issues)

        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_issue, parent, false))

    override fun getItemCount() = itemDataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(itemDataset[position])

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(viewState: IssueViewState) {
            with(itemView as IssueCard) {
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