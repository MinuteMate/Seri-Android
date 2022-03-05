package com.hackaton.seriandroid.ui.auth.name

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
    private val viewModel: AuthViewModel by viewModels()
    val name: String by lazy {
        binding.authInputNameText.text.toString()
    }

    override fun initView() {
        binding.fragment = this@AuthNameInputFragment
    }

    private fun inputName() = name.isNotEmpty()


    fun nextButtonClickEvent() {
        if (inputName()) {
            showShortToast("다시 입력해 주세요.")
        } else {
            viewModel.getName(name)

            // 네비에기션 후 오자.
            findNavController().navigate(R.id.navigation_header_container)
        }
    }
}