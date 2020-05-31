package com.mbiamont.github.repository.details

import android.os.Bundle
import com.mbiamont.github.design.navigation.TAB_FORKS
import com.mbiamont.github.design.navigation.TAB_ISSUES
import com.mbiamont.github.design.navigation.TAB_PULL_REQUESTS
import com.mbiamont.github.repository.forks.ForksFragment
import com.mbiamont.github.repository.issues.IssuesFragment
import com.mbiamont.github.repository.pullrequests.PullRequestsFragment

class FragmentAdapter {

    private var issuesFragment: IssuesFragment? = null
    private var pullRequestsFragment: PullRequestsFragment? = null
    private var forksFragment: ForksFragment? = null

    fun getFragmentByTabPosition(extras: Bundle?, position: Int) = when (position) {
        TAB_ISSUES -> issuesFragment ?: IssuesFragment.create(extras).apply {
            issuesFragment = this
        }
        TAB_PULL_REQUESTS -> pullRequestsFragment ?: PullRequestsFragment.create(extras).apply {
            pullRequestsFragment = this
        }
        TAB_FORKS -> forksFragment ?: ForksFragment.create(extras).apply {
            forksFragment = this
        }
        else -> error("No fragment at postion $position")
    }
}