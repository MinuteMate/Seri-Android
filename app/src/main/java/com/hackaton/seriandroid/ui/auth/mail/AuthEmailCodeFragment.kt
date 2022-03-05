package com.hackaton.seriandroid.ui.auth.mail

import android.content.Context
import android.os.CountDownTimer
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hackaton.seriandroid.R
import com.hackaton.seriandroid.databinding.FragmentAuthEmailCodeBinding
import com.hackaton.seriandroid.ui.auth.AuthMainActivity
import com.hackaton.seriandroid.ui.auth.AuthViewModel
import com.hackaton.seriandroid.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint

class AuthEmailCodeFragment : BaseFragment<FragmentAuthEmailCodeBinding>
    (R.layout.fragment_auth_email_code) {


    private val viewModel: AuthViewModel by activityViewModels()
    private val args by navArgs<AuthEmailCodeFragmentArgs>()

    private val frameLayout: FrameLayout by lazy {
        binding.authOtpFrameLayout
    }
    private val otpTextOne: EditText by lazy {
        binding.emailCodeOneText
    }
    private val otpTextTwo: EditText by lazy {
        binding.emailCodeTwoText
    }
    private val otpTextThree: EditText by lazy {
        binding.emailCodeThreeText
    }
    private val otpTextFour: EditText by lazy {
        binding.emailCodeFourText
    }
    private val otpTextFive: EditText by lazy {
        binding.emailCodeFiveText
    }
    private val otpTextSix: EditText by lazy {
        binding.emailCodeSixText
    }
    private val mCountDown: CountDownTimer = object : CountDownTimer(300001, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            //update the UI with the new count
            binding.authEmailCodeTime.text =
                ("남은시간 ${millisUntilFinished % 3600000 / 60000}: ${millisUntilFinished % 3600000 % 60000 / 1000}")
        }

        override fun onFinish() {
            binding.authEmailCodeReSend.visibility = View.VISIBLE
            binding.authEmailCodeTime.visibility = View.INVISIBLE


        }
    }

    private fun setListener() {
        frameLayout.setOnClickListener {
            val inputManager =
                (activity as AuthMainActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(frameLayout.windowToken, 0)
        }
        setTextChangeListener(fromEditText = otpTextOne, targetEditText = otpTextTwo)
        setTextChangeListener(fromEditText = otpTextTwo, targetEditText = otpTextThree)
        setTextChangeListener(fromEditText = otpTextThree, targetEditText = otpTextFour)
        setTextChangeListener(fromEditText = otpTextFour, targetEditText = otpTextFive)
        setTextChangeListener(fromEditText = otpTextFive, targetEditText = otpTextSix)
        setTextChangeListener(fromEditText = otpTextSix, done = {
            verifyOTPCode()
        })
        setKeyListener(fromEditText = otpTextTwo, backToEditText = otpTextOne)
        setKeyListener(fromEditText = otpTextThree, backToEditText = otpTextTwo)
        setKeyListener(fromEditText = otpTextFour, backToEditText = otpTextThree)
        setKeyListener(fromEditText = otpTextFive, backToEditText = otpTextFour)
        setKeyListener(fromEditText = otpTextSix, backToEditText = otpTextFive)
    }


    override fun initView() {
        postEmailCode()
        binding.authEmailCodeContent.text = "${args.email}으로 6자리 코드를를 전송했습니다."
        setListener()
        initFocus()
        mCountDown.start()
        binding.fragment = this@AuthEmailCodeFragment

    }

    fun timeReSendOnClickEvent() {
        binding.authEmailCodeReSend.setOnClickListener {
            mCountDown.start()
            binding.authEmailCodeReSend.visibility = View.INVISIBLE
            binding.authEmailCodeTime.visibility = View.VISIBLE
        }
    }


    private fun initFocus() {
        otpTextOne.isEnabled = true
        otpTextOne.postDelayed({
            otpTextOne.requestFocus()
            val inputMethodManager =
                (activity as AuthMainActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(otpTextOne, InputMethodManager.SHOW_FORCED)
        }, 500)
    }

    private fun reset() {
        otpTextOne.isEnabled = false
        otpTextTwo.isEnabled = false
        otpTextThree.isEnabled = false
        otpTextFour.isEnabled = false
        otpTextFive.isEnabled = false
        otpTextSix.isEnabled = false

        otpTextOne.setText("")
        otpTextTwo.setText("")
        otpTextThree.setText("")
        otpTextFour.setText("")
        otpTextFive.setText("")
        otpTextSix.setText("")
        initFocus()
    }

    private fun setTextChangeListener(
        fromEditText: EditText,
        targetEditText: EditText? = null,
        done: (() -> Unit)? = null
    ) {
        fromEditText.addTextChangedListener {
            it?.let { string ->
                if (string.isNotEmpty()) {

                    targetEditText?.let { editText ->
                        editText.isEnabled = true
                        editText.requestFocus()

                    } ?: run {
                        done?.let { done ->
                            done()

                        }
                    }
                    fromEditText.clearFocus()
                    fromEditText.isEnabled = false
                }

            }

        }
    }

    private fun setKeyListener(fromEditText: EditText, backToEditText: EditText) {
        fromEditText.setOnKeyListener { _, _, event ->
            if (null != event && KeyEvent.KEYCODE_DEL == event.keyCode) {
                backToEditText.isEnabled = true
                backToEditText.requestFocus()
                backToEditText.setText("")

                fromEditText.clearFocus()
                fromEditText.includeFontPadding = true
            }
            false

        }
    }


    private fun verifyOTPCode() {
        val otpCode = otpTextOne.text.toString().trim() +
                otpTextTwo.text.toString().trim() +
                otpTextThree.text.toString().trim() +
                otpTextFour.text.toString().trim() +
                otpTextFive.text.toString().trim() +
                otpTextSix.text.toString().trim()



        if (otpCode.length != 6)
            return

        lifecycleScope.launch {
            viewModel.fetchVerifyCode(otpCode)

            viewModel.optSuccess.observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().navigate(R.id.action_authEmailCodeFragment_to_authPasswordFragment)

                } else {
                    showShortToast("데이터가 없습니다.")
                    reset()

                }
            }
            viewModel.optFail.observe(viewLifecycleOwner) {
                showShortToast(it)

            }

            val inputManager =
                (activity as AuthMainActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(frameLayout.windowToken, 0)
        }

    }

    private fun postEmailCode() {
        if (args.email.isNotEmpty()) {
            lifecycleScope.launch {
                viewModel.postAuthorizeEmail(email = args.email)
            }
        } else {
            showShortToast("코드 전송이 실패했습니다.")
        }
    }
}

