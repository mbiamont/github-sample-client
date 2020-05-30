package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.RepositoryDetails
import com.mbiamont.github.domain.entity.RepositoryExtract
import com.mbiamont.github.service.graphql.FetchRepositoryDetailsQuery
import com.mbiamont.github.service.graphql.FetchUserPublicRepositoriesQuery

class RemoteRepositoryMapper(
    private val userMapper: IRemoteUserMapper,
    private val languageMapper: IRemoteLanguageMapper
) : IRemoteRepositoryMapper {

    override fun map(repo: FetchUserPublicRepositoriesQuery.Node) = RepositoryExtract(
        name = repo.name(),
        owner = userMapper.map(repo.owner()),
        mainLanguage = languageMapper.map(repo.languages()?.nodes()?.firstOrNull()),
        starsCount = repo.stargazers().totalCount()
    )

    override fun map(repo: FetchRepositoryDetailsQuery.Repository) = RepositoryDetails(
        name = repo.name(),
        owner = userMapper.map(repo.owner()),
        description = repo.description() ?: "",
        mainLanguage = languageMapper.map(repo.languages()?.nodes()?.firstOrNull()),
        starsCount = repo.stargazers().totalCount(),
        issuesCount = repo.issues().totalCount(),
        pullRequestsCount = repo.pullRequests().totalCount(),
        forksCount = repo.forks().totalCount()
    )
}