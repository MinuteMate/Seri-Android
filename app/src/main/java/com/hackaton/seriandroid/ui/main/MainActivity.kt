package com.hackaton.seriandroid.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.ActivityMainBinding
import com.hackaton.seriandroid.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding> (
    R.layout.activity_main
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
    }
}