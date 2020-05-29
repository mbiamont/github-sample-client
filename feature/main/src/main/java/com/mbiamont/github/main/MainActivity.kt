package com.mbiamont.github.main

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbiamont.github.core.android.BaseActivity
import com.mbiamont.github.core.android.extensions.observe
import com.mbiamont.github.core.android.extensions.with
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private val repositoryExtractAdapter = RepositoryExtractAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupObservers()

        mainViewModel.onViewReady()
    }

    private fun setupView() {
        with(repositoryList) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = repositoryExtractAdapter
        }
    }

    private fun setupObservers() {
        observe(mainViewModel.repositoriesLiveData) with {
            repositoryExtractAdapter.setItems(it)
        }

        observe(mainViewModel.errorMessageLiveData) with {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}