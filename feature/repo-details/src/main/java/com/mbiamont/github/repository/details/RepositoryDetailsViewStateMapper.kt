package com.mbiamont.github.repository.details

import com.mbiamont.github.core.android.IColorProvider
import com.mbiamont.github.domain.entity.Language
import com.mbiamont.github.domain.entity.RepositoryDetails

class RepositoryDetailsViewStateMapper(
    private val colorProvider: IColorProvider
) : IRepositoryDetailsViewStateMapper {

    override fun map(repositoryDetails: RepositoryDetails) = RepositoryDetailsViewState(
        ownerName = repositoryDetails.owner.login,
        ownerAvatarUrl = repositoryDetails.owner.avatarUrl,
        repositoryName = repositoryDetails.name,
        repositoryDescription = repositoryDetails.description,
        mainLanguage = map(repositoryDetails.mainLanguage),
        starCountLabel = repositoryDetails.starsCount.toString(),
        pullRequestCountLabel = repositoryDetails.pullRequestsCount.toString(),
        forkCountLabel = repositoryDetails.forksCount.toString()
    )

    private fun map(language: Language?) = language?.let { MainLanguageViewState(it.name, colorProvider.parse(it.color)) }
}