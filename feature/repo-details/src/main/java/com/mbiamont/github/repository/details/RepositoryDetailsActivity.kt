package com.mbiamont.github.repository.details

import android.os.Bundle
import android.view.View
import coil.api.load
import coil.transform.CircleCropTransformation
import com.mbiamont.github.core.android.BaseActivity
import com.mbiamont.github.core.android.extensions.observe
import com.mbiamont.github.core.android.extensions.with
import kotlinx.android.synthetic.main.activity_repository_details.*
import kotlinx.android.synthetic.main.layout_navigation_view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class RepositoryDetailsActivity : BaseActivity() {

    private val viewModel: RepositoryDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_details)
        setupObservers()

        bottomNavigationBar.onTabItemSelected = {
            Timber.i("[MELVIN] TAB SELECTED = $it")
        }

        viewModel.onViewReady(intent.extras)
    }

    private fun setupObservers() {
        observe(viewModel.repositoryDetailsLiveData) with {
            it.ownerAvatarUrl?.let { avatarUrl ->
                icOwnerAvatar.load(avatarUrl) {
                    transformations(CircleCropTransformation())
                }
            }
            lblOwnerLogin.text = it.ownerName
            lblRepositoryName.text = it.repositoryName
            lblRepositoryDescription.text = it.repositoryDescription

            if (it.mainLanguage != null) {
                lblMainLanguageValue.text = it.mainLanguage.label
                lblMainLanguageValue.setTextColor(it.mainLanguage.color)
            } else {
                lblMainLanguage.visibility = View.GONE
                lblMainLanguageValue.visibility = View.GONE
            }

            lblStarCountValue.text = it.starCountLabel
            lblPullRequestsCountValue.text = it.pullRequestCountLabel
            lblForkCountValue.text = it.forkCountLabel
        }
    }
}