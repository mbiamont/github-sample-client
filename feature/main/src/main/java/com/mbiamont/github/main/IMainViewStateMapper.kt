package com.mbiamont.github.main

import com.mbiamont.github.domain.entity.RepositoryExtract

interface IMainViewStateMapper {

    fun map(repositoryExtract: RepositoryExtract): RepositoryExtractViewState
}