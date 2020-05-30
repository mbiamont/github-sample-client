package com.mbiamont.github.repository.issues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssuesFragment : Fragment() {

    private val viewModel by viewModel<IssuesViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_issues, container, false)

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
