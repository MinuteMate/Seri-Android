package com.hackaton.seriandroid.ui.auth.name

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.FragmentAuthNameInputBinding
import com.hackaton.seriandroid.ui.auth.AuthViewModel
import com.hackaton.seriandroid.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthNameInputFragment :
    BaseFragment<FragmentAuthNameInputBinding>(R.layout.fragment_auth_name_input) {
    private val viewModel: AuthViewModel by activityViewModels()


    override fun initView() {
        binding.fragment = this@AuthNameInputFragment
    }

    private fun inputName() = binding.authInputNameText.text.toString()
        .isEmpty()


    fun nextButtonClickEvent() {
        if (inputName()) {
            showShortToast("다시 입력해 주세요.")
        } else {
            viewModel.getName(binding.authInputNameText.text.toString())

            // 네비에기션 후 오자.
            findNavController().navigate(R.id.action_authNameInputFragment_to_authFinishFragment)
        }
    }
}