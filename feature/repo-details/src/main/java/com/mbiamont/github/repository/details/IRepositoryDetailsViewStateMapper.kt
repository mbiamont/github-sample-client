package com.mbiamont.github.repository.details

import com.mbiamont.github.domain.entity.RepositoryDetails

interface IRepositoryDetailsViewStateMapper {

    fun map(repositoryDetails: RepositoryDetails): RepositoryDetailsViewState
}