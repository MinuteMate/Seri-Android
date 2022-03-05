package com.hackaton.seriandroid.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hackaton.seriandroid.data.remote.dto.request.SignInRequest
import com.hackaton.seriandroid.data.remote.dto.request.SignUpRequest
import com.hackaton.seriandroid.data.remote.network.ResultWrapper
import com.hackaton.seriandroid.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _name

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _name

    private val _success = MutableLiveData<Boolean>()
    val success: MutableLiveData<Boolean> get() = _success

    private val _optSuccess = MutableLiveData<Boolean>()
    val optSuccess: MutableLiveData<Boolean> get() = _optSuccess


    private val _fail = MutableLiveData<String>()
    val fail: MutableLiveData<String> get() = _fail

    private val _optFail = MutableLiveData<String>()
    val optFail: MutableLiveData<String> get() = _optFail
    private val _emailToken = MutableLiveData<String>()
    val emailToken: LiveData<String> get() = _emailToken


    fun getName(name: String) {
        _name.value = name
    }


    fun getEmail(email: String) {
        _email.value = email

    }

    fun getPassword(password: String) {
        _password.value = password

    }

    suspend fun postAuthorizeEmail(email: String) {
        when (val response = repository.sendEmail(email)) {
            is ResultWrapper.Success -> response.value
            is ResultWrapper.Failed -> Log.d("TAG", "postAuthorizeEmail: ${response.code}")
        }

    }

    suspend fun fetchVerifyCode(number: String) {

        when (val response = repository.verifyEmail(number)) {
            is ResultWrapper.Success -> {
                _emailToken.value = response.value?.token
                _optSuccess.value = response.value != null
            }
            is ResultWrapper.Failed -> {
                _optFail.value = response.error?.message
            }
        }

    }

    suspend fun postSignUp() {


        val response = repository.signUp(
            SignUpRequest(
                _emailToken.value.toString(),
                _name.value.toString(),
                _password.value.toString()
            )
        )
        when (response) {
            is ResultWrapper.Success -> {
                Log.d("TAG", "postSignUp: ${response}")
                _success.value = response.value != null
            }
            is ResultWrapper.Failed -> {
                _fail.value = response.error?.message
            }
            is ResultWrapper.NetworkError -> {
                _fail.value = "네트워크 에러"
            }
        }
    }

    suspend fun postLogin(id: String, pwd: String) {
        val response = repository.signIn(
            SignInRequest(
                id, pwd
            )
        )
        when (response) {
            is ResultWrapper.Success -> {
                Log.d("TAG", "postLogin: ${response}")
                _success.value = true
            }
            is ResultWrapper.Failed -> {
                _fail.value = response.error?.message
            }
        }


    }
}