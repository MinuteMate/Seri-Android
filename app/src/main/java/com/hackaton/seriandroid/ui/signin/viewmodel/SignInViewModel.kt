package com.hackaton.seriandroid.ui.signin.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackaton.seriandroid.data.remote.dto.request.SignInRequest
import com.hackaton.seriandroid.data.remote.network.ResultWrapper
import com.hackaton.seriandroid.data.repository.AuthRepository
import com.hackaton.seriandroid.utils.MutableEventFlow
import com.hackaton.seriandroid.utils.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun signIn(signInRequest: SignInRequest) {
        viewModelScope.launch {

            when (val signIn = authRepository.signIn(signInRequest)) {
                is ResultWrapper.NetworkError -> {
                    event(Event.ErrorMessage("네트워크가 불안정합니다."))
                }
                is ResultWrapper.Failed -> {
                    event(Event.ErrorMessage(signIn.error?.message!!))
                }
                is ResultWrapper.Success -> {
                    event(Event.Success(true))
                }
            }
        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class Success(val success: Boolean) : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}