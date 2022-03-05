package com.hackaton.seriandroid.ui.auth.login

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.FragmentAuthLoginBinding
import com.hackaton.seriandroid.ui.auth.AuthMainActivity
import com.hackaton.seriandroid.ui.auth.AuthViewModel
import com.hackaton.seriandroid.ui.base.BaseBottomSheetDialogFragment
import com.hackaton.seriandroid.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class AuthLoginFragment :
    BaseBottomSheetDialogFragment<FragmentAuthLoginBinding>(R.layout.fragment_auth_login) {

    private val viewModel: AuthViewModel by viewModels()
    override fun initView() {
        binding.fragment = this@AuthLoginFragment

        with(viewModel) {
            success.observe(viewLifecycleOwner) {
                // 메인으로 이동
                this@AuthLoginFragment.dismiss()

            }
            fail.observe(viewLifecycleOwner) {
                showLongToast(it)
            }
        }
    }

    fun signUpButtonClickEvent() {
        startActivity(Intent(requireContext(), AuthMainActivity::class.java))
        this@AuthLoginFragment.dismiss()
    }

    fun loginButtonClickEvent() {
        lifecycleScope.launch {
            viewModel.postLogin(
                id = binding.authLoginIdText.text.toString(),
                pwd = binding.authLoginPwdText.text.toString()
            )
        }
    }

}