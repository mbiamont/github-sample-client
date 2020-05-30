package com.mbiamont.github.repository.list

import android.graphics.Color
import com.mbiamont.github.domain.entity.RepositoryExtract

class RepositoryListViewStateMapper : IRepositoryListViewStateMapper {

    override fun map(repositoryExtract: RepositoryExtract) = RepositoryExtractViewState(
        name = repositoryExtract.name,
        starsCount = repositoryExtract.starsCount.toString(),
        languageViewState = repositoryExtract.mainLanguage?.let {
            LanguageViewState(
                it.name,
                Color.parseColor(it.color)
            )
        },
        ownerLogin = repositoryExtract.owner.login
    )
}