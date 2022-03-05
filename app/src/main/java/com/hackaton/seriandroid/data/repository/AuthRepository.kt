package com.hackaton.seriandroid.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.hackaton.seriandroid.data.local.datasource.LocalAuthDataSource
import com.hackaton.seriandroid.data.remote.datasource.RemoteAuthDataSource
import com.hackaton.seriandroid.data.remote.dto.request.SignInRequest
import com.hackaton.seriandroid.data.remote.dto.request.SignUpRequest
import com.hackaton.seriandroid.data.remote.dto.response.FetchVerifyCodeResponse
import com.hackaton.seriandroid.data.remote.dto.response.SignInResponse
import com.hackaton.seriandroid.data.remote.dto.response.SignUpResponse
import com.hackaton.seriandroid.data.remote.network.ResultWrapper
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteAuthDataSource: RemoteAuthDataSource,
    private val localAuthDataSource: LocalAuthDataSource
) {

    suspend fun signIn(signInRequest: SignInRequest): ResultWrapper<Unit?> {

        return when (val call = remoteAuthDataSource.signIn(signInRequest)) {
            is ResultWrapper.NetworkError -> ResultWrapper.NetworkError
            is ResultWrapper.Failed -> ResultWrapper.Failed()
            is ResultWrapper.Success -> {
                saveAccount(signInRequest)
                call.value?.let { saveToken(it) }
                ResultWrapper.Success(Unit)
            }
        }
    }

    private suspend fun saveAccount(signInRequest: SignInRequest) {
        localAuthDataSource.apply {
            setId(signInRequest.email)
            setPw(signInRequest.password)
        }
    }

    private suspend fun saveToken(signInResponse: SignInResponse) {
        localAuthDataSource.apply {
            setAccessToken(signInResponse.accessToken)
            setRefreshToken(signInResponse.refreshToken)
        }
    }

    suspend fun autoLogin(): ResultWrapper<SignInResponse?> =
        remoteAuthDataSource.signIn(
            SignInRequest(
                localAuthDataSource.fetchId(),
                localAuthDataSource.fetchPw()
            )
        )

    suspend fun signUp(signUpRequest: SignUpRequest): ResultWrapper<SignUpResponse?> =
        remoteAuthDataSource.signUp(signUpRequest)

    suspend fun sendEmail(email: String): ResultWrapper<Unit?> =
        remoteAuthDataSource.sendEmail(email)

    suspend fun verifyEmail(number: String): ResultWrapper<FetchVerifyCodeResponse?> =
        remoteAuthDataSource.verifyEmail(number)

    suspend fun checkSignInEmailPolicy(email: String): ResultWrapper<Unit?> =
        remoteAuthDataSource.checkSignInEmailPolicy(email)

    suspend fun checkSignInPasswordPolicy(password: String): ResultWrapper<Unit?> =
        remoteAuthDataSource.checkSignInPasswordPolicy(password)

    suspend fun checkSignUpEmailPolicy(email: String): ResultWrapper<Unit?> =
        remoteAuthDataSource.checkSignUpEmailPolicy(email)

    suspend fun checkSignUpNicknamePolicy(nickname: String): ResultWrapper<Unit?> =
        remoteAuthDataSource.checkSignUpNicknamePolicy(nickname)

    suspend fun checkSignUpPasswordPolicy(password: String): ResultWrapper<Unit?> =
        remoteAuthDataSource.checkSignUpPasswordPolicy(password)
}