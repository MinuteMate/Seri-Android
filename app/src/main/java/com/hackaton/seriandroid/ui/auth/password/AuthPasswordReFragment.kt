package com.hackaton.seriandroid.ui.auth.password

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.FragmentAuthPasswordReBinding
import com.hackaton.seriandroid.ui.auth.AuthViewModel
import com.hackaton.seriandroid.ui.base.BaseFragment

class AuthPasswordReFragment :
    BaseFragment<FragmentAuthPasswordReBinding>(R.layout.fragment_auth_password_re) {
    val password: String by lazy {
        viewModel.password.value.toString()
    }
    val rePassword: String by lazy {
        binding.passwordText.text.toString()
    }
    private val viewModel: AuthViewModel by viewModels()
    override fun initView() {

    }

    fun nextButtonClickEvent() {
        if (password == rePassword) {
            findNavController().navigate(R.id.action_authPasswordReFragment_to_authNameInputFragment)
        } else {
            showShortToast("비밀번호가 일치하지 않습니다.")
        }
    }
}