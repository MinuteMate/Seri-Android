package com.hackaton.seriandroid.ui.auth.password

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.FragmentAuthPasswordBinding
import com.hackaton.seriandroid.ui.auth.AuthViewModel
import com.hackaton.seriandroid.ui.base.BaseFragment

class AuthPasswordFragment :
    BaseFragment<FragmentAuthPasswordBinding>(R.layout.fragment_auth_password) {
    private val password: String by lazy {
        binding.passwordText.text.toString()
    }
    private val viewModel: AuthViewModel by activityViewModels()
    override fun initView() {
        binding.fragment = this@AuthPasswordFragment
        viewModel.success.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_authPasswordFragment_to_authPasswordReFragment)
        }
    }

    private fun passwordNullTest() = password.isNotEmpty()

    fun nextButtonClickEvent() {
        if (passwordNullTest()) {
            viewModel.getPassword(password)
        } else {
            showShortToast("다시 입력해 주세요.")
        }
    }
}