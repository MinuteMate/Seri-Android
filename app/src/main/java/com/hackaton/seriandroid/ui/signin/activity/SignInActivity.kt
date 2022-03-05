package com.hackaton.seriandroid.ui.signin.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.data.remote.dto.request.SignInRequest
import com.hackaton.seriandroid.databinding.ActivitySignInBinding
import com.hackaton.seriandroid.ui.auth.AuthMainActivity
import com.hackaton.seriandroid.ui.base.BaseActivity
import com.hackaton.seriandroid.ui.main.MainActivity
import com.hackaton.seriandroid.ui.signin.viewmodel.SignInViewModel
import com.hackaton.seriandroid.utils.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(
    R.layout.activity_sign_in
) {

    private val vm: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: SignInViewModel.Event) = when (event) {
        is SignInViewModel.Event.Success -> {
            successLogin()
        }
        is SignInViewModel.Event.ErrorMessage -> {
            Toast.makeText(applicationContext, event.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun successLogin() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun initView() {
        binding.tvSignIn.setOnClickListener {
            val email = binding.etSignInEmail.text.toString()
            val password = binding.etSignInPassword.text.toString()

            when {
                email.isEmpty() -> {
                    showShortToast("이메일을 입력해주세요.")
                }
                password.isEmpty() -> {
                    showShortToast("비밀번호를 입력해주세요.")
                }
                else -> {
                    vm.signIn(SignInRequest(email, password))
                }
            }
        }

        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, AuthMainActivity::class.java))
        }
    }
}