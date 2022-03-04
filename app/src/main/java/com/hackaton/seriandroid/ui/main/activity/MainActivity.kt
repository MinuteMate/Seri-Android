package com.hackaton.seriandroid.ui.main.activity

import android.os.Bundle
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.ActivityMainBinding
import com.hackaton.seriandroid.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
    }
}