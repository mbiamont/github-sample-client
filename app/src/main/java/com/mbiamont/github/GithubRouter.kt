package com.mbiamont.github

import com.mbiamont.github.domain.navigation.LOGIN
import com.mbiamont.github.domain.navigation.MAIN
import com.mbiamont.github.domain.navigation.SPLASH
import com.mbiamont.github.login.LoginActivity
import com.mbiamont.github.main.MainActivity
import com.mbiamont.github.navigation.IRouter
import com.mbiamont.github.navigation.route
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

        MAIN -> route<MainActivity> {
            finishActivity = true
            clearActivityStack = true
        }

        else -> error("Destination $destination doesn't exist")
    }
}