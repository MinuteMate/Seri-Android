package com.hackaton.seriandroid.ui.main

import android.content.Intent
import android.os.Bundle
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.ActivityMainBinding
import com.hackaton.seriandroid.ui.base.BaseActivity
import com.hackaton.seriandroid.ui.game.activity.BeforeActivity

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
        startActivity(Intent(this, BeforeActivity::class.java))
    }

    private fun createGame(gameName: String) {
        startActivity(Intent(this, BeforeActivity::class.java))
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
        showShortToast("success")
    }
}