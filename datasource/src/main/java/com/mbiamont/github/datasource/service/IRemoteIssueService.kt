package com.mbiamont.github.datasource.service

import com.mbiamont.github.core.Monad
import com.mbiamont.github.domain.entity.Issue
import java.util.*

interface IRemoteIssueService {

    suspend fun getRepositoryIssues(repositoryName: String, ownerLogin: String, since: Date): Monad<List<Issue>>
}