package com.mbiamont.github.repository.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbiamont.github.core.android.BaseActivity
import com.mbiamont.github.core.android.extensions.observe
import com.mbiamont.github.core.android.extensions.with
import kotlinx.android.synthetic.main.activity_repository_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryListActivity : BaseActivity() {

    private val repositoryListViewModel: RepositoryListViewModel by viewModel()

    private val repositoryExtractAdapter = RepositoryExtractAdapter { name, ownerLogin ->
        repositoryListViewModel.onRepositoryExtractClicked(name, ownerLogin)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_list)
        setupView()
        setupObservers()

        repositoryListViewModel.onViewReady()
    }

    private fun setupView() {
        shimmerLayout.startShimmerAnimation()

        with(repositoryList) {
            layoutManager = LinearLayoutManager(this@RepositoryListActivity)
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = repositoryExtractAdapter
        }
    }

    private fun setupObservers() {
        observe(repositoryListViewModel.repositoriesLiveData) with {
            repositoryExtractAdapter.setItems(it)

            shimmerLayout.stopShimmerAnimation()
            shimmerLayout.visibility = View.GONE
            repositoryList.visibility = View.VISIBLE
        }

        observe(repositoryListViewModel.errorMessageLiveData) with {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}