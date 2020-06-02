package com.mbiamont.github.repository.pullrequests

import android.content.Context
import android.text.format.DateUtils
import com.mbiamont.github.core.android.provider.IDateUtilsProvider
import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState
import com.mbiamont.github.domain.entity.PullRequest

class PullRequestsViewStateMapper(
    private val context: Context,
    private val dateUtilsProvider: IDateUtilsProvider
) : IPullRequestsViewStateMapper {

    override fun map(isLoading: Boolean) = ProgressViewState(
        isLoading = isLoading
    )

    override fun map(pullRequestsPerWeek: Array<Int>) = TimeSerieViewState(
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
        dateLabel = dateUtilsProvider.getRelativeDateTimeString(
            context,
            pullRequest.createdAt.timeInMillis,
            DateUtils.DAY_IN_MILLIS,
            DateUtils.WEEK_IN_MILLIS,
            0
        ).toString(),
        commentCountLabel = pullRequest.commentsCount.toString(),
        pullRequestId = pullRequest.id
    )
}