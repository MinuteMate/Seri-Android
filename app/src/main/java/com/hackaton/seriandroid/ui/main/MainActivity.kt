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
        binding.tvCreateGame.setOnClickListener {
            createGameDialog()
        }
        binding.ivUserSet.setOnClickListener {
            fixProfileDialog()
        }
    }

    private fun classCodeDialog() {
        GameCodeDialog(this).run {
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

    private fun createGameDialog() {
        CreateGameDialog(this).run {
            gameName.observe(this@MainActivity, {
                createGame(it)
            })
            start()
        }
    }

    private fun joinClass(gameCode: Int) {
        showShortToast(gameCode.toString())
    }

    private fun createGame(gameName: String) {
        showShortToast(gameName.toString())
    }

    private fun fixProfileDialog() {
        FixUserProfileDialog(this).run {
            nickname.observe(this@MainActivity, {
                fixProfile(it)
            })
            start()
        }
    }

    private fun fixProfile(nickname: String) {

    }
}