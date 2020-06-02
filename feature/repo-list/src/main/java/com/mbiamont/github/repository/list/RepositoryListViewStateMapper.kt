package com.mbiamont.github.repository.list

import com.mbiamont.github.core.android.provider.IColorProvider
import com.mbiamont.github.domain.entity.RepositoryExtract

class RepositoryListViewStateMapper(
    private val colorProvider: IColorProvider
) : IRepositoryListViewStateMapper {

    override fun map(repositoryExtract: RepositoryExtract) = RepositoryExtractViewState(
        name = repositoryExtract.name,
        starsCount = repositoryExtract.starsCount.toString(),
        languageViewState = repositoryExtract.mainLanguage?.let {
            LanguageViewState(
                it.name,
                colorProvider.parse(it.color)
            )
        },
        ownerLogin = repositoryExtract.owner.login
    )
}