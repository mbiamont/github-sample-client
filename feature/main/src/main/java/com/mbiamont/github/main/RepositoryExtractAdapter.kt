package com.mbiamont.github.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_repository.view.*

class RepositoryExtractAdapter : RecyclerView.Adapter<RepositoryExtractAdapter.ViewHolder>() {

    private val dataset: MutableList<RepositoryExtractViewState> = mutableListOf()

    fun setItems(list: List<RepositoryExtractViewState>) {
        dataset.clear()
        dataset.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_repository, parent, false)
    )

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(dataset[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(repositoryExtract: RepositoryExtractViewState) {
            itemView.lblRepositoryName.text = repositoryExtract.name
            itemView.lblStarCount.text = repositoryExtract.starsCount

            itemView.lblLanguage.visibility = if (repositoryExtract.languageViewState == null) View.GONE else View.VISIBLE

            repositoryExtract.languageViewState?.let {
                itemView.lblLanguage.text = it.language
                itemView.lblLanguage.setTextColor(it.languageColor)
            }
        }

    }
}