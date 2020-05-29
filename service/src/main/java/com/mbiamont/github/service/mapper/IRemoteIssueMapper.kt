package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.Issue
import com.mbiamont.github.service.graphql.FetchRepositoryIssuesQuery

interface IRemoteIssueMapper {

    fun map(issue: FetchRepositoryIssuesQuery.Node): Issue
}