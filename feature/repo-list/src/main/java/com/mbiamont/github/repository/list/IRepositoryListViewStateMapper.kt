package com.mbiamont.github.repository.list

import com.mbiamont.github.domain.entity.RepositoryExtract

interface IRepositoryListViewStateMapper {

    fun map(repositoryExtract: RepositoryExtract): RepositoryExtractViewState
}