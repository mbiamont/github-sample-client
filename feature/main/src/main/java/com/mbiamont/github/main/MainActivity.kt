package com.mbiamont.github.main

import android.os.Bundle
import com.mbiamont.github.core.android.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}