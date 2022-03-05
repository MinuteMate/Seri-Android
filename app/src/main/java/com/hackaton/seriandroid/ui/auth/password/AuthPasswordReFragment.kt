package com.hackaton.seriandroid.ui.auth.password

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.FragmentAuthPasswordReBinding
import com.hackaton.seriandroid.ui.auth.AuthViewModel
import com.hackaton.seriandroid.ui.auth.mail.AuthEmailCodeFragmentArgs
import com.hackaton.seriandroid.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class AuthPasswordReFragment :
    BaseFragment<FragmentAuthPasswordReBinding>(R.layout.fragment_auth_password_re) {


    private val args by navArgs<AuthPasswordReFragmentArgs>()

    private val viewModel: AuthViewModel by activityViewModels()
    override fun initView() {
        nextButtonClickEvent()
    }

    private fun passwordNullTest() = binding.passwordText.text.toString() == args.password

    fun nextButtonClickEvent() {
        binding.nextBtn.setOnClickListener {

            if (passwordNullTest()) {
                viewModel.getPassword(binding.passwordText.text.toString())
                findNavController().navigate(R.id.action_authPasswordReFragment_to_authNameInputFragment)

            } else {
                showShortToast("다시 입력해 주세요.")

            }
        }
    }
}