package com.hackaton.seriandroid.ui.auth.mail

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.FragmentAuthEmailInputBinding
import com.hackaton.seriandroid.ui.auth.AuthViewModel
import com.hackaton.seriandroid.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthEmailInputFragment : BaseFragment<FragmentAuthEmailInputBinding>
    (R.layout.fragment_auth_email_input) {

    private val viewModel: AuthViewModel by activityViewModels()
    private val email: String by lazy {
        binding.authInputEmailText.text.toString()

    }

    override fun initView() {
        binding.fragment = this@AuthEmailInputFragment

        viewModel.success.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_authEmailInputFragment_to_authEmailCodeFragment)
        }
    }


    private fun inputEmail() = email.isNotEmpty()


    fun nextButtonClickEvent() {
        if (inputEmail()) {
            showShortToast("다시 입력해 주세요.")
        } else {
            viewModel.getEmail(email)
            // 네비에기션 후 오자.
            findNavController().navigate(R.id.navigation_header_container)
        }
    }

}