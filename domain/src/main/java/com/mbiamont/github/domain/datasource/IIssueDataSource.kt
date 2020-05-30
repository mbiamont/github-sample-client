package com.mbiamont.github.domain.datasource

import com.mbiamont.github.core.Monad
import com.mbiamont.github.domain.entity.Issue
import java.util.*

interface IIssueDataSource {

    suspend fun getRepositoryIssues(repositoryName: String, ownerLogin: String, since: Date): Monad<List<Issue>>
}