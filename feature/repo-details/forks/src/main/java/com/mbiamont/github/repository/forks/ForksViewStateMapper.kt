package com.mbiamont.github.repository.forks

import android.content.Context
import android.text.format.DateUtils
import com.mbiamont.github.core.android.viewstate.ProgressViewState
import com.mbiamont.github.core.android.viewstate.TimeSerieViewState
import com.mbiamont.github.domain.entity.Fork

class ForksViewStateMapper(
    private val context: Context
) : IForksViewStateMapper {

    override fun map(isLoading: Boolean) = ProgressViewState(
        isLoading = isLoading
    )

    override fun map(forksPerWeek: Array<Int>) = TimeSerieViewState(
        dataset = forksPerWeek
    )

    override fun map(fork: Fork) = ForkViewState(
        ownerAvatarUrl = fork.owner?.avatarUrl,
        ownerLogin = fork.owner?.login,
        dateLabel = DateUtils.getRelativeDateTimeString(
            context,
            fork.createdAt.timeInMillis,
            DateUtils.DAY_IN_MILLIS,
            DateUtils.WEEK_IN_MILLIS,
            0
        ).toString()
    )
}