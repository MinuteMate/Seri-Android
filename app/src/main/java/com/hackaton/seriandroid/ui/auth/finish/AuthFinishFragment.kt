package com.hackaton.seriandroid.ui.auth.finish

import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.FragmentAuthFinishBinding
import com.hackaton.seriandroid.ui.auth.AuthViewModel
import com.hackaton.seriandroid.ui.base.BaseFragment
import com.hackaton.seriandroid.ui.splash.activity.SplashActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthFinishFragment : BaseFragment<FragmentAuthFinishBinding>(R.layout.fragment_auth_finish) {
    private val viewModel: AuthViewModel by activityViewModels()

    override fun initView() {
        binding.fragment=this@AuthFinishFragment
        with(viewModel) {
            fail.observe(viewLifecycleOwner) {
                showShortToast(it)
            }
            success.observe(viewLifecycleOwner) {
                if (it)
                    startActivity(Intent(requireContext(), SplashActivity::class.java))
                else{
                    showShortToast("회원가입 할 수 없는 계정입니다.")
                }
            }
        }
    }

    fun nextButtonClickEvent() {

        lifecycleScope.launch {
            viewModel.postSignUp()
        }
    }
}