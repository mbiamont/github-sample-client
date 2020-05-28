package com.mbiamont.github.core.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    private val navigator: INavigator by inject()

    override fun onRestart() {
        super.onRestart()
        navigator.attachCurrentActivity(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.attachCurrentActivity(this)
    }

    override fun onDestroy() {
        navigator.detachCurrentActivity(this)
        super.onDestroy()
    }
}