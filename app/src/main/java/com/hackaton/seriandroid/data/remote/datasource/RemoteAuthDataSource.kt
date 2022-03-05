package com.hackaton.seriandroid.data.remote.datasource

import com.hackaton.seriandroid.data.remote.dto.request.*
import com.hackaton.seriandroid.data.remote.dto.response.*
import com.hackaton.seriandroid.data.remote.network.ResultWrapper

interface RemoteAuthDataSource {

    suspend fun signIn(signInRequest: SignInRequest): ResultWrapper<SignInResponse?>

    suspend fun reissueToken(refreshToken: String): ResultWrapper<ReissueTokenResponse?>

    suspend fun signUp(signUpRequest: SignUpRequest): ResultWrapper<SignUpResponse?>

    suspend fun sendEmail(email: String): ResultWrapper<Unit?>

    suspend fun verifyEmail(number: String): ResultWrapper<FetchVerifyCodeResponse?>

    suspend fun checkSignInEmailPolicy(email: String): ResultWrapper<Unit?>

    suspend fun checkSignInPasswordPolicy(password: String): ResultWrapper<Unit?>

    suspend fun checkSignUpEmailPolicy(email: String): ResultWrapper<Unit?>

    suspend fun checkSignUpNicknamePolicy(nickname: String): ResultWrapper<Unit?>

    suspend fun checkSignUpPasswordPolicy(password: String): ResultWrapper<Unit?>
}