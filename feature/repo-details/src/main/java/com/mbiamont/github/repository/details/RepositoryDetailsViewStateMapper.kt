package com.mbiamont.github.repository.details

import android.graphics.Color
import com.mbiamont.github.domain.entity.Language
import com.mbiamont.github.domain.entity.RepositoryDetails

class RepositoryDetailsViewStateMapper : IRepositoryDetailsViewStateMapper {

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

    private fun map(language: Language?) = language?.let { MainLanguageViewState(it.name, Color.parseColor(it.color)) }
}