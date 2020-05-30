package com.mbiamont.github.repository.details

import android.os.Bundle
import android.view.View
import coil.api.load
import coil.transform.CircleCropTransformation
import com.mbiamont.github.core.android.BaseActivity
import com.mbiamont.github.core.android.extensions.observe
import com.mbiamont.github.core.android.extensions.with
import com.mbiamont.github.design.navigation.TAB_ISSUES
import com.mbiamont.github.repository.issues.IssuesFragment
import kotlinx.android.synthetic.main.activity_repository_details.*
import kotlinx.android.synthetic.main.layout_navigation_view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class RepositoryDetailsActivity : BaseActivity() {

    private val viewModel: RepositoryDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_details)
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
    }

    private fun setupNavigation() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentHost, IssuesFragment.create(intent.extras)).commit()

        supportFragmentManager.addOnBackStackChangedListener {
            when (supportFragmentManager.findFragmentById(R.id.fragmentHost)) {
                is IssuesFragment -> bottomNavigationBar.selectedTab = TAB_ISSUES
                //TODO OTHER TABS
            }
        }

        bottomNavigationBar.onTabItemSelected = {
            Timber.i("[MELVIN] TAB SELECTED = $it")
            supportFragmentManager.beginTransaction().replace(
                R.id.fragmentHost, when (it) {
                    TAB_ISSUES -> IssuesFragment.create(intent.extras)
                    else -> error("Tab not managed")
                }
            ).addToBackStack(null).commit()
        }
    }
}