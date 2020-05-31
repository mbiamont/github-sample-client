package com.mbiamont.github.repository.pullrequests

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbiamont.github.core.android.extensions.observe
import com.mbiamont.github.core.android.extensions.with
import kotlinx.android.synthetic.main.fragment_pull_requests.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullRequestsFragment : Fragment(R.layout.fragment_pull_requests) {

    private val viewModel by viewModel<PullRequestsViewModel>()
    private val pullRequestAdapter = PullRequestsAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        setupObservers()

        viewModel.onViewReady(arguments)
    }

    private fun setupView() {
        timeSerieGraph.title = getString(R.string.pullRequestsTitle)

        with(pullRequestsList) {
            val manager = LinearLayoutManager(requireContext())
            layoutManager = manager
            setHasFixedSize(true)
            adapter = pullRequestAdapter
        }
    }

    private fun setupObservers() {
        observe(viewModel.pullRequestsListLiveData) with {
            pullRequestAdapter.updateViewState(it)
        }

        observe(viewModel.progressLiveData) with {
            timeSerieGraph.showLoader(it.progress, it.total, it.progressLabel)
        }

        observe(viewModel.timeSerieLiveData) with {
            timeSerieGraph.showDatas(it.dataset)
        }
    }

    companion object {
        fun create(extras: Bundle?) = PullRequestsFragment().apply {
            arguments = extras
        }
    }
}
