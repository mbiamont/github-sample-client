package com.mbiamont.github.repository.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import coil.load
import coil.transform.CircleCropTransformation
import com.mbiamont.github.core.android.BaseActivity
import com.mbiamont.github.core.android.extensions.observe
import com.mbiamont.github.core.android.extensions.with
import com.mbiamont.github.design.navigation.TAB_FORKS
import com.mbiamont.github.design.navigation.TAB_ISSUES
import com.mbiamont.github.design.navigation.TAB_PULL_REQUESTS
import com.mbiamont.github.repository.forks.ForksFragment
import com.mbiamont.github.repository.issues.IssuesFragment
import com.mbiamont.github.repository.pullrequests.PullRequestsFragment
import kotlinx.android.synthetic.main.activity_repository_details.*
import kotlinx.android.synthetic.main.layout_navigation_view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryDetailsActivity : BaseActivity(R.layout.activity_repository_details) {

    private val viewModel: RepositoryDetailsViewModel by viewModel()
    private val fragmentAdapter = FragmentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation()
        setupObservers()

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

        observe(viewModel.errorMessageLiveData) with {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupNavigation() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHost, fragmentAdapter.getFragmentByTabPosition(intent.extras, TAB_ISSUES)).commit()

        supportFragmentManager.addOnBackStackChangedListener {
            when (supportFragmentManager.findFragmentById(R.id.fragmentHost)) {
                is IssuesFragment -> bottomNavigationBar.selectedTab = TAB_ISSUES
                is PullRequestsFragment -> bottomNavigationBar.selectedTab = TAB_PULL_REQUESTS
                is ForksFragment -> bottomNavigationBar.selectedTab = TAB_FORKS
            }
        }

        bottomNavigationBar.onTabItemSelected = {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragmentHost, fragmentAdapter.getFragmentByTabPosition(intent.extras, it)
            ).addToBackStack(null).commit()
        }
    }
}