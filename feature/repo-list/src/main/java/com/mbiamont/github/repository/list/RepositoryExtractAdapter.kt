package com.mbiamont.github.repository.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbiamont.github.design.card.RepositoryExtractCard
import com.mbiamont.github.design.extensions.calculateDiff

class RepositoryExtractAdapter(private val onItemClickListener: (name: String, ownerLogin: String) -> Unit) :
    RecyclerView.Adapter<RepositoryExtractAdapter.ViewHolder>() {

    private val dataset: MutableList<RepositoryExtractViewState> = mutableListOf()

    fun addAll(items: List<RepositoryExtractViewState>) {
        val diff = calculateDiff(dataset, items) { old, new ->
            old.name == new.name && old.ownerLogin == new.ownerLogin
        }
        dataset.clear()
        dataset.addAll(items)


        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_repository, parent, false)
    )

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(dataset[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(repositoryExtract: RepositoryExtractViewState) {
            with(itemView as RepositoryExtractCard) {
                title = repositoryExtract.name
                starLabel = repositoryExtract.starsCount

                if (repositoryExtract.languageViewState != null) {
                    showLanguage(repositoryExtract.languageViewState.language, repositoryExtract.languageViewState.languageColor)
                } else {
                    hideLanguage()
                }

                setOnClickListener {
                    onItemClickListener(repositoryExtract.name, repositoryExtract.ownerLogin)
                }
            }
        }

    }
}