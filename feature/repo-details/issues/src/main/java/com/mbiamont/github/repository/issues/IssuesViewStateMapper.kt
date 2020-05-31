package com.mbiamont.github.repository.issues

import android.content.Context
import android.text.format.DateUtils
import com.mbiamont.github.domain.entity.Issue
import java.lang.String.format

class IssuesViewStateMapper(
    private val context: Context
) : IIssuesViewStateMapper {

    override fun map(count: Int, totalCount: Int, issuesPerWeek: Array<Int>?): IssueTimeSerieViewState {
        val percent = (count.toFloat() / totalCount.toFloat()) * 100
        return IssueTimeSerieViewState(
            downloaded = count,
            total = totalCount,
            progressLabel = format("%.2f", percent) + "%",
            dataset = issuesPerWeek
        )
    }

    override fun map(issue: Issue): IssueViewState {
        val time = System.currentTimeMillis() - issue.createdAt.timeInMillis

        return IssueViewState(
            indexColor = context.resources.getColor(R.color.colorAccent),
            ownerAvatarUrl = issue.author?.avatarUrl,
            ownerLogin = issue.author?.login,
            title = issue.title,
            dateLabel = DateUtils.getRelativeDateTimeString(
                context,
                time,
                DateUtils.DAY_IN_MILLIS,
                DateUtils.WEEK_IN_MILLIS,
                0
            ).toString(),
            commentCountLabel = issue.commentsCount.toString()
        )
    }
}