package com.hackaton.seriandroid.ui.auth.mail

import android.util.Log
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

    override fun initView() {
        binding.fragment = this@AuthEmailInputFragment

        viewModel.success.observe(viewLifecycleOwner) {
            val action =
                AuthEmailInputFragmentDirections.actionAuthEmailInputFragmentToAuthEmailCodeFragment(
                    binding.authInputEmailText.text.toString().trim()
                )
            findNavController().navigate(action)
        }
    }


    fun nextButtonClickEvent() {
        if (binding.authInputEmailText.text.toString().isBlank()) {
            Log.d("TAG", "nextButtonClickEvent: ")
            showShortToast("다시 입력해 주세요.")
        } else {
            Log.d("TAG", "nextButtonClickEvent: ${binding.authInputEmailText.text.toString()}")
            viewModel.getEmail(binding.authInputEmailText.text.toString())
        }
    }

}