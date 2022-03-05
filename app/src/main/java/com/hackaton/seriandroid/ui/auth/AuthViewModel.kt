package com.hackaton.seriandroid.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel  @Inject constructor(): ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _name

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _name

    private val _success = MutableLiveData<Boolean>()
    val success: MutableLiveData<Boolean> get() = _success

    fun getName(name: String) {
        _name.value = name
        _success.value=true
    }

    fun getEmail(email: String) {
        _email.value = email
        _success.value=true

    }

    fun getPassword(password: String) {
        _password.value = password
        _success.value=true

    }
}