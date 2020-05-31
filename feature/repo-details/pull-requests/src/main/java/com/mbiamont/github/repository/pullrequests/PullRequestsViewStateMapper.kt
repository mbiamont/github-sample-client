package com.mbiamont.github.repository.pullrequests

import android.content.Context
import android.text.format.DateUtils
import com.mbiamont.github.domain.entity.PullRequest

class PullRequestsViewStateMapper(
    private val context: Context
) : IPullRequestsViewStateMapper {

    override fun map(count: Int, totalCount: Int, pullRequestsPerWeek: Array<Int>?) = PullRequestTimeSerieViewState(
        downloaded = count,
        total = totalCount,
        progressLabel = "${((count.toFloat() / totalCount.toFloat()) * 100).toInt()}%",
        dataset = pullRequestsPerWeek
    )

    override fun map(pullRequest: PullRequest) = PullRequestViewState(
        indexColor = context.resources.getColor(
            when (pullRequest.state) {
                PullRequest.State.OPEN -> R.color.green
                PullRequest.State.CLOSED -> R.color.red
                PullRequest.State.MERGED -> R.color.violet
                PullRequest.State.UNKNOWN -> R.color.lightGrey
            }
        ),
        ownerAvatarUrl = pullRequest.author?.avatarUrl,
        ownerLogin = pullRequest.author?.login,
        title = pullRequest.title,
        dateLabel = DateUtils.getRelativeDateTimeString(
            context,
            pullRequest.createdAt.timeInMillis,
            DateUtils.DAY_IN_MILLIS,
            DateUtils.WEEK_IN_MILLIS,
            0
        ).toString(),
        commentCountLabel = pullRequest.commentsCount.toString()
    )
}