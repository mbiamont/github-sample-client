package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.RepositoryDetails
import com.mbiamont.github.domain.entity.RepositoryExtract
import com.mbiamont.github.service.graphql.FetchRepositoryDetailsQuery
import com.mbiamont.github.service.graphql.FetchUserPublicRepositoriesQuery

class RemoteRepositoryMapper(
    private val userMapper: IRemoteUserMapper
) : IRemoteRepositoryMapper {

    override fun map(node: FetchUserPublicRepositoriesQuery.Node) = RepositoryExtract(
        name = node.name(),
        owner = userMapper.map(node.owner()),
        starsCount = node.stargazers().totalCount()
    )

    override fun map(repo: FetchRepositoryDetailsQuery.Repository) = RepositoryDetails(
        name = repo.name(),
        owner = userMapper.map(repo.owner()),
        starsCount = repo.stargazers().totalCount(),
        issuesCount = repo.issues().totalCount(),
        pullRequestsCount = repo.pullRequests().totalCount(),
        forksCount = repo.forks().totalCount()
    )
}