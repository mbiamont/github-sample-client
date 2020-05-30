package com.mbiamont.github.repository.pullrequests

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullRequestsFragment : Fragment(R.layout.fragment_pull_requests) {

    private val viewModel by viewModel<PullRequestsViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.onViewReady(arguments)
    }

    companion object {
        fun create(extras: Bundle?) = PullRequestsFragment().apply {
            arguments = extras
        }
    }
}
