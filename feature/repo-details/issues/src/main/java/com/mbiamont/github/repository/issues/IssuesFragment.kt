package com.mbiamont.github.repository.issues

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mbiamont.github.core.android.extensions.observe
import com.mbiamont.github.core.android.extensions.with
import kotlinx.android.synthetic.main.fragment_issues.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssuesFragment : Fragment(R.layout.fragment_issues) {

    private val viewModel by viewModel<IssuesViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()

        viewModel.onViewReady(arguments)
    }

    private fun setupObservers() {
        observe(viewModel.issuesViewStateLiveData) with {
            timeSerieGraph.showLoader(it.timeSerie.downloaded, it.timeSerie.total, it.timeSerie.progressLabel)

            it.timeSerie.dataset?.let {
                timeSerieGraph.showDatas(it)
            }
        }
    }

    companion object {
        fun create(extras: Bundle?) = IssuesFragment().apply {
            arguments = extras
        }
    }
}
