package com.mbiamont.github.repository.issues

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssuesFragment : Fragment(R.layout.fragment_issues) {

    private val viewModel by viewModel<IssuesViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.onViewReady(arguments)
    }

    companion object {
        fun create(extras: Bundle?) = IssuesFragment().apply {
            arguments = extras
        }
    }
}
