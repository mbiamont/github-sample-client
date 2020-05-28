package com.mbiamont.github.splashscreen

import android.os.Bundle
import com.mbiamont.github.core.android.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        splashViewModel.onViewReady()
    }
}