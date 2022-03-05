package com.hackaton.seriandroid.ui.game.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.ActivityVoteBinding
import com.hackaton.seriandroid.ui.base.BaseActivity
import com.hackaton.seriandroid.utils.visible

class VoteActivity : BaseActivity<ActivityVoteBinding> (
    R.layout.activity_vote
) {

    private var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movePage(page++)

        binding.ivSend.setOnClickListener {
            movePage(page++)
        }
        mCountDown.start()
    }

    private val mCountDown: CountDownTimer = object : CountDownTimer(60000, 1000) {
        override fun onTick(millisUntilFinished: Long) {

            binding.textView8.text =
                ("남은시간 ${millisUntilFinished % 600000 % 60000 / 1000}")

            binding.progressBar.progress = (millisUntilFinished % 600000 % 60000 / 1000).toInt()
        }

        override fun onFinish() {
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
                    v31.visible()
                    v32.visible()
                    v33.visible()
                    v34.visible()
                    v35.visible()
                    v36.visible()
                }
                4 -> {
                    v3P1.visible()
                    v3P2.visible()
                    v3P3.visible()
                    v3P4.visible()
                    v3P5.visible()
                }
                5 -> {
                    v5.visible()
                }
                6 -> {
                    v6.visible()
                }
                7 -> {
                    v7.visible()
                }
            }
        }
    }

    override fun initView() {
    }
}