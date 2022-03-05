package com.hackaton.seriandroid.ui.auth.finish

import androidx.fragment.app.activityViewModels
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.FragmentAuthFinishBinding
import com.hackaton.seriandroid.ui.auth.AuthViewModel
import com.hackaton.seriandroid.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFinishFragment : BaseFragment<FragmentAuthFinishBinding>(R.layout.fragment_auth_finish) {
    private val viewModel: AuthViewModel by activityViewModels()
    override fun initView() {
    }

    fun nextButtonClickEvent() {

        // 회원가입 코드
    }
}