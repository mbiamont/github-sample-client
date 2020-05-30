package com.mbiamont.github.main

import android.graphics.Color
import com.mbiamont.github.domain.entity.RepositoryExtract

class MainViewStateMapper : IMainViewStateMapper {

    override fun map(repositoryExtract: RepositoryExtract) = RepositoryExtractViewState(
        name = repositoryExtract.name,
        starsCount = repositoryExtract.starsCount.toString(),
        languageViewState = repositoryExtract.mainLanguage?.let { LanguageViewState(it.name, Color.parseColor(it.color)) },
        ownerLogin = repositoryExtract.owner.login
    )
}