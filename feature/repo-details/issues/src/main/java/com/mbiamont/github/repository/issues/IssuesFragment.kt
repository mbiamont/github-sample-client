package com.mbiamont.github.repository.issues

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbiamont.github.core.android.extensions.observe
import com.mbiamont.github.core.android.extensions.with
import kotlinx.android.synthetic.main.fragment_issues.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssuesFragment : Fragment(R.layout.fragment_issues) {

    private val viewModel by viewModel<IssuesViewModel>()
    private val issuesAdapter = IssuesAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        setupObservers()

        viewModel.onViewReady(arguments)
    }

    private fun setupView() {
        timeSerieGraph.title = getString(R.string.issuesTitle)

        with(issuesList) {
            val manager = LinearLayoutManager(requireContext())
            layoutManager = manager
            setHasFixedSize(true)
            adapter = issuesAdapter
        }
    }

    private fun setupObservers() {
        observe(viewModel.issuesListLiveData) with {
            issuesAdapter.updateViewState(it)
        }

        observe(viewModel.progressLiveData) with {
            if(it.isLoading){
                timeSerieGraph.showLoader()
            } else {
                timeSerieGraph.hideLoader()
            }
        }

        observe(viewModel.timeSerieLiveData) with {
            timeSerieGraph.showDatas(it.dataset)
        }
    }

    companion object {
        fun create(extras: Bundle?) = IssuesFragment().apply {
            arguments = extras
        }
    }
}
