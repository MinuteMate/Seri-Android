package com.hackaton.seriandroid.data.remote.datasource

import com.hackaton.seriandroid.data.remote.api.AuthApi
import com.hackaton.seriandroid.data.remote.dto.request.SignInRequest
import com.hackaton.seriandroid.data.remote.dto.request.SignUpRequest
import com.hackaton.seriandroid.data.remote.dto.response.FetchVerifyCodeResponse
import com.hackaton.seriandroid.data.remote.dto.response.ReissueTokenResponse
import com.hackaton.seriandroid.data.remote.dto.response.SignInResponse
import com.hackaton.seriandroid.data.remote.dto.response.SignUpResponse
import com.hackaton.seriandroid.data.remote.network.ResultWrapper
import com.hackaton.seriandroid.data.remote.network.safeApiCall
import javax.inject.Inject

class RemoteAuthDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
) : RemoteAuthDataSource {

    override suspend fun signIn(signInRequest: SignInRequest): ResultWrapper<SignInResponse?> =
        safeApiCall {
            authApi.signIn(signInRequest)
        }

    override suspend fun reissueToken(refreshToken: String): ResultWrapper<ReissueTokenResponse?> =
        safeApiCall {
            authApi.reissueToken(refreshToken)
        }

    override suspend fun signUp(signUpRequest: SignUpRequest): ResultWrapper<SignUpResponse?> =
        safeApiCall {
            authApi.signUp(signUpRequest)
        }

    override suspend fun sendEmail(email: String): ResultWrapper<Unit?> =
        safeApiCall {
            authApi.postAuthorizeEmail(email)
        }

    override suspend fun verifyEmail(number: String): ResultWrapper<FetchVerifyCodeResponse?> =
        safeApiCall {
            authApi.fetchVerifyCode(number)
        }

    override suspend fun checkSignInEmailPolicy(email: String): ResultWrapper<Unit?> =
        safeApiCall {
            authApi.checkSignInEmailPolicy(email)
        }

    override suspend fun checkSignInPasswordPolicy(password: String): ResultWrapper<Unit?> =
        safeApiCall {
            authApi.checkSignInPasswordPolicy(password)
        }

    override suspend fun checkSignUpEmailPolicy(email: String): ResultWrapper<Unit?> =
        safeApiCall {
            authApi.checkSignUpEmailPolicy(email)
        }

    override suspend fun checkSignUpNicknamePolicy(nickname: String): ResultWrapper<Unit?> =
        safeApiCall {
            authApi.checkSignUpNicknamePolicy(nickname)
        }

    override suspend fun checkSignUpPasswordPolicy(password: String): ResultWrapper<Unit?> =
        safeApiCall {
            authApi.checkSignUpPasswordPolicy(password)
        }
}