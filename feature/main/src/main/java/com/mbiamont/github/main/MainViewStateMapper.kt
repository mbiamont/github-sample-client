package com.mbiamont.github.main

import android.graphics.Color
import com.mbiamont.github.domain.entity.RepositoryExtract

class MainViewStateMapper : IMainViewStateMapper {

    override fun map(repositoryExtract: RepositoryExtract): RepositoryExtractViewState {
        return RepositoryExtractViewState(
            name = repositoryExtract.name,
            starsCount = "${repositoryExtract.starsCount} ⭐︎",
            languageViewState = repositoryExtract.mainLanguage?.let { LanguageViewState(it.name, Color.parseColor(it.color)) }
        )
    }
}