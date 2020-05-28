package com.mbiamont.github.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf
import com.mbiamont.github.core.android.BaseActivity
import com.mbiamont.github.core.android.INavigator

class AndroidNavigator(
    private val router: IRouter,
    private val context: Context
) : INavigator {

    private var currentBaseActivity: BaseActivity? = null

    override fun attachCurrentActivity(activity: BaseActivity) {
        currentBaseActivity = activity
    }

    override fun detachCurrentActivity(activity: BaseActivity) {
        if (currentBaseActivity == activity) {
            currentBaseActivity = null
        }
    }

    override fun navigateTo(route: String, extras: Map<String, Any>?) {
        val context = currentBaseActivity ?: context
        val parsedRoute = router.parse(route)
        val intent = Intent(context, parsedRoute.destinationActivity)

        extras?.let {
            val bundledExtras = bundleOf(*it.map { extra -> extra.key to extra.value }.toTypedArray())
            intent.putExtras(bundledExtras)
        }

        if (parsedRoute.clearActivityStack) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        context.startActivity(intent)

        if (parsedRoute.finishActivity && context is Activity) {
            context.finish()
        }
    }

    override fun navigateBack() {
        currentBaseActivity?.let {
            if (it.supportFragmentManager.backStackEntryCount <= 1) {
                it.finish()
            } else {
                it.supportFragmentManager.popBackStack()
            }
        }
    }

}