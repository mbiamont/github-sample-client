package com.mbiamont.github.repository.forks

import android.content.Context
import android.text.format.DateUtils
import com.mbiamont.github.domain.entity.Fork

class ForksViewStateMapper(
    private val context: Context
) : IForksViewStateMapper {

    override fun map(count: Int, totalCount: Int, forksPerWeek: Array<Int>?) = ForkTimeSerieViewState(
        downloaded = count,
        total = totalCount,
        progressLabel = "${((count.toFloat() / totalCount.toFloat()) * 100).toInt()}%",
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