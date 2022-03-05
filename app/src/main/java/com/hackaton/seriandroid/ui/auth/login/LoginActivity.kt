package com.hackaton.seriandroid.ui.auth.login

import android.content.Intent
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.ActivityLoginBinding
import com.hackaton.seriandroid.ui.auth.AuthMainActivity
import com.hackaton.seriandroid.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    fun loginButtonClickEvent() {
        val dialog = AuthLoginFragment()
        dialog.show(supportFragmentManager, "loginDialog")
    }

    fun signUpButtonClickEvent() {
        startActivity(Intent(this, AuthMainActivity::class.java))

    }

    override fun initView() {
        binding.activity = this@LoginActivity
    }

}