package com.mbiamont.github.repository.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbiamont.github.core.android.BaseActivity
import com.mbiamont.github.core.android.extensions.observe
import com.mbiamont.github.core.android.extensions.with
import com.mbiamont.github.design.extensions.InfiniteScrollListener
import com.mbiamont.github.design.extensions.createInfiniteScrollListener
import kotlinx.android.synthetic.main.activity_repository_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryListActivity : BaseActivity() {

    private val viewModel: RepositoryListViewModel by viewModel()

    private lateinit var scrollListener: InfiniteScrollListener

    private val repositoryExtractAdapter = RepositoryExtractAdapter { name, ownerLogin ->
        viewModel.onRepositoryExtractClicked(name, ownerLogin)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_list)
        setupView()
        setupObservers()

        viewModel.onViewReady()
    }

    private fun setupView() {
        shimmerLayout.startShimmerAnimation()

        with(repositoryList) {
            val manager = LinearLayoutManager(this@RepositoryListActivity)
            layoutManager = manager
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = repositoryExtractAdapter

            scrollListener = createInfiniteScrollListener(manager) {
                viewModel.onRepositoriesScrolled()
            }

            addOnScrollListener(scrollListener)
        }
    }

    private fun setupObservers() {
        observe(viewModel.repositoriesLiveData) with {
            repositoryExtractAdapter.addAll(it)

            shimmerLayout.stopShimmerAnimation()
            shimmerLayout.visibility = View.GONE
            repositoryList.visibility = View.VISIBLE
        }

        observe(viewModel.errorMessageLiveData) with {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        with(repositoryList){
            clearOnScrollListeners()
            layoutManager = null
            adapter = null
        }
        super.onDestroy()
    }
}