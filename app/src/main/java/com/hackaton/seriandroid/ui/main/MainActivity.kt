package com.hackaton.seriandroid.ui.main

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
        binding.tvPlayGame.setOnClickListener {
            classCodeDialog()
        }
        binding.ivHelp.setOnClickListener {
            helpDialog()
        }
    }

    private fun classCodeDialog() {
        RoomCodeDialog(this).run {
            classCode.observe(this@MainActivity, {
                joinClass(it.toInt())
            })
            start()
        }
    }

    private fun helpDialog() {
        GuideDialog(this).run {
            start()
        }
    }

    private fun joinClass(classCode: Int) {
        showShortToast(classCode.toString())
    }
}