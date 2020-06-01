package com.mbiamont.github.repository.forks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbiamont.github.design.card.ForkCard
import com.mbiamont.github.design.extensions.calculateDiff

class ForksAdapter : RecyclerView.Adapter<ForksAdapter.ItemViewHolder>() {

    private val itemDataset = mutableListOf<ForkViewState>()

    fun updateViewState(issues: List<ForkViewState>) {
        val diff = calculateDiff(itemDataset, issues) { old, new ->
            old.forkId == new.forkId
        }
        itemDataset.clear()
        itemDataset.addAll(issues)

        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_fork, parent, false))

    override fun getItemCount() = itemDataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(itemDataset[position])

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(viewState: ForkViewState) {
            with(itemView as ForkCard) {
                author = viewState.ownerLogin ?: ""
                dateLabel = viewState.dateLabel
                setAvatarUrl(viewState.ownerAvatarUrl ?: "")
            }
        }
    }
}