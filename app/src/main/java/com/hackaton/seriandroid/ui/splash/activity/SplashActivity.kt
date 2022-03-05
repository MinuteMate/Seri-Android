package com.hackaton.seriandroid.ui.splash.activity

import android.annotation.SuppressLint
import android.content.Intent
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.ActivitySplashBinding
import com.hackaton.seriandroid.ui.auth.AuthMainActivity
import com.hackaton.seriandroid.ui.signin.activity.SignInActivity
import com.hackaton.seriandroid.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(
    R.layout.activity_splash
) {
    override fun initView() {
        binding.tvSplashSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        binding.tvSplashSignUp.setOnClickListener {
            val intent = Intent(this, AuthMainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}