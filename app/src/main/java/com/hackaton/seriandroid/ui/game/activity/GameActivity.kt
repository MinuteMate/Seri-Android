package com.hackaton.seriandroid.ui.game.activity

import android.content.Intent
import android.os.Bundle
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.ActivityGameBinding
import com.hackaton.seriandroid.ui.base.BaseActivity
import com.hackaton.seriandroid.utils.visible

class GameActivity : BaseActivity<ActivityGameBinding>(
    R.layout.activity_game
) {

    private var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movePage(++page)

        binding.cll.setOnClickListener {
            movePage(++page)
        }

    }

    private fun movePage(page: Int) {
        binding.run {
            when (page) {
                1 -> f1.visible()
                2 -> f2.visible()
                3 -> f3.visible()
                4 -> f4.visible()
                5 -> f5.visible()
                6 -> f6.visible()
                7 -> f7.visible()
                8 -> f8.visible()
                9 -> f9.visible()
                10 -> f10.visible()
                11 -> f11.visible()
                12 -> f12.visible()
                13 -> f13.visible()
                14 -> f14.visible()
                15 -> f15.visible()
                16 -> f16.visible()
                17 -> f17.visible()
                18 -> f18.visible()
                19 -> f19.visible()
                20 -> f20.visible()
                21 -> f21.visible()
                22 -> f22.visible()
                23 -> {
                    startActivity(Intent(this@GameActivity, VoteActivity::class.java))
                }
            }
        }
    }

    override fun initView() {
    }
}