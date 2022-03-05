package com.hackaton.seriandroid.ui.game.activity

import android.content.Intent
import android.os.Bundle
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.ActivityBeforeBinding
import com.hackaton.seriandroid.ui.base.BaseActivity
import com.hackaton.seriandroid.utils.visible

class BeforeActivity : BaseActivity<ActivityBeforeBinding> (
    R.layout.activity_before
) {

    private var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movePage(++page)

        binding.ivbSend.setOnClickListener {
            movePage(++page)
        }

    }

    private fun movePage(page: Int) {
        binding.run {
            when (page) {
                1 -> {
                    v11.visible()
                    v12.visible()
                    v13.visible()
                }
                2 -> {
                    v21.visible()
                    v22.visible()
                    v23.visible()
                }
                3 -> {
                    v3.visible()
                }
                4 -> {
                    v41.visible()
                    v42.visible()
                    v43.visible()
                }
                5 -> {
                    v51.visible()
                    v52.visible()
                    v53.visible()
                }
                6 -> {
                    v61.visible()
                    v62.visible()
                    v63.visible()
                }
                7 -> {
                    v71.visible()
                    v72.visible()
                    v73.visible()
                }
                8 -> {
                    v81.visible()
                    v82.visible()
                    v83.visible()
                }
                9 -> {
                    v91.visible()
                    v92.visible()
                    v93.visible()
                }
                10 -> {
                    v101.visible()
                    v102.visible()
                    v103.visible()
                }
                11 -> {
                    v111.visible()
                }
                12 -> {
                    startActivity(Intent(this@BeforeActivity, GameActivity::class.java))
                }
            }
        }
    }

    override fun initView() {
    }
}