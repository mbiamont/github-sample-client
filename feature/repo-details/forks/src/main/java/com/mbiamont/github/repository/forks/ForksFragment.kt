package com.mbiamont.github.repository.forks

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForksFragment : Fragment(R.layout.fragment_forks) {

    private val viewModel by viewModel<ForksViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.onViewReady(arguments)
    }

    companion object {
        fun create(extras: Bundle?) = ForksFragment().apply {
            arguments = extras
        }
    }
}
