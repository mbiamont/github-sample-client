package com.mbiamont.github.repository.pullrequests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullRequestsFragment : Fragment() {

    private val viewModel by viewModel<PullRequestsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_pull_requests, container, false)

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
