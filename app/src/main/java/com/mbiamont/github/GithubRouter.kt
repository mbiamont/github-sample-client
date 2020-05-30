package com.mbiamont.github

import com.mbiamont.github.domain.navigation.LOGIN
import com.mbiamont.github.domain.navigation.REPOS_LIST
import com.mbiamont.github.domain.navigation.REPO_DETAILS
import com.mbiamont.github.domain.navigation.SPLASH
import com.mbiamont.github.login.LoginActivity
import com.mbiamont.github.repository.list.RepositoryListActivity
import com.mbiamont.github.navigation.IRouter
import com.mbiamont.github.navigation.route
import com.mbiamont.github.repository.details.RepositoryDetailsActivity
import com.mbiamont.github.splashscreen.SplashActivity

class GithubRouter : IRouter {

    override fun parse(destination: String) = when (destination) {
        SPLASH -> route<SplashActivity> {
            finishActivity = true
        }

        LOGIN -> route<LoginActivity> {
            finishActivity = true
            clearActivityStack = true
        }

        REPOS_LIST -> route<RepositoryListActivity> {
            finishActivity = true
            clearActivityStack = true
        }

        REPO_DETAILS -> route<RepositoryDetailsActivity> {
            finishActivity = false
            clearActivityStack = false
        }

        else -> error("Destination $destination doesn't exist")
    }
}