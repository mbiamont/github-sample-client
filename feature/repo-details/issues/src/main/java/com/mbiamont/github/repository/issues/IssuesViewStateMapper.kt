package com.mbiamont.github.repository.issues

import android.content.Context
import android.text.format.DateUtils
import com.mbiamont.github.domain.entity.Issue
import java.lang.String.format

class IssuesViewStateMapper(
    private val context: Context
) : IIssuesViewStateMapper {

    override fun map(count: Int, totalCount: Int, issuesPerWeek: Array<Int>?) = IssueTimeSerieViewState(
        downloaded = count,
        total = totalCount,
        progressLabel = "${((count.toFloat() / totalCount.toFloat()) * 100).toInt()}%",
        dataset = issuesPerWeek
    )

    override fun map(issue: Issue) = IssueViewState(
        indexColor = context.resources.getColor(
            when (issue.state) {
                Issue.State.OPEN -> R.color.green
                Issue.State.CLOSED -> R.color.red
                Issue.State.UNKNOWN -> R.color.violet
            }
        ),
        ownerAvatarUrl = issue.author?.avatarUrl,
        ownerLogin = issue.author?.login,
        title = issue.title,
        dateLabel = DateUtils.getRelativeDateTimeString(
            context,
            issue.createdAt.timeInMillis,
            DateUtils.DAY_IN_MILLIS,
            DateUtils.WEEK_IN_MILLIS,
            0
        ).toString(),
        commentCountLabel = issue.commentsCount.toString()
    )
}