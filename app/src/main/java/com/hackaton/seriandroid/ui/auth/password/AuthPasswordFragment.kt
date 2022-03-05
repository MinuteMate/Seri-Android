package com.hackaton.seriandroid.ui.auth.password

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.FragmentAuthPasswordBinding
import com.hackaton.seriandroid.ui.auth.AuthViewModel
import com.hackaton.seriandroid.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class AuthPasswordFragment :
    BaseFragment<FragmentAuthPasswordBinding>(R.layout.fragment_auth_password) {

    private val viewModel: AuthViewModel by activityViewModels()
    override fun initView() {

        nextButtonClickEvent()
    }

    private fun passwordNullTest() = binding.passwordText.text.toString().isNotEmpty()

    fun nextButtonClickEvent() {
        binding.nextBtn.setOnClickListener {
            if (passwordNullTest()) {
                viewModel.getPassword(binding.passwordText.text.toString())
                val action =
                    AuthPasswordFragmentDirections.actionAuthPasswordFragmentToAuthPasswordReFragment(
                        binding.passwordText.text.toString()
                    )
                findNavController().navigate(action)
            } else {
                showShortToast("다시 입력해 주세요.")

            }
        }
    }
}