package com.hackaton.seriandroid.data.repository

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

    suspend fun signIn(signInRequest: SignInRequest): ResultWrapper<SignInResponse?> {
        val response = remoteAuthDataSource.signIn(signInRequest)

        when (response) {
            is ResultWrapper.Success -> saveAccount(signInRequest)
        }

        return response
    }

    private suspend fun saveAccount(signInRequest: SignInRequest) {
        localAuthDataSource.apply {
            setId(signInRequest.email)
            setPw(signInRequest.password)
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