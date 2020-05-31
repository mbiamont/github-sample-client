package com.mbiamont.github.repository.forks

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbiamont.github.core.android.extensions.observe
import com.mbiamont.github.core.android.extensions.with
import kotlinx.android.synthetic.main.fragment_forks.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForksFragment : Fragment(R.layout.fragment_forks) {

    private val viewModel by viewModel<ForksViewModel>()
    private val forkAdapter = ForksAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        setupObservers()

        viewModel.onViewReady(arguments)
    }

    private fun setupView() {
        timeSerieGraph.title = getString(R.string.forksTitle)

        with(forksList) {
            val manager = LinearLayoutManager(requireContext())
            layoutManager = manager
            setHasFixedSize(true)
            adapter = forkAdapter
        }
    }

    private fun setupObservers() {
        observe(viewModel.forkListLiveData) with {
            forkAdapter.updateViewState(it)
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
        fun create(extras: Bundle?) = ForksFragment().apply {
            arguments = extras
        }
    }
}
