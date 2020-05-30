package com.mbiamont.github.repository.details

import android.os.Bundle
import com.mbiamont.github.core.android.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryDetailsActivity : BaseActivity() {

    private val repositoryDetailsViewModel: RepositoryDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_details)

        repositoryDetailsViewModel.onViewReady(intent.extras)
    }
}