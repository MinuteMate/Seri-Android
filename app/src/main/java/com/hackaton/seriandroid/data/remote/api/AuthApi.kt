package com.hackaton.seriandroid.data.remote.api

import com.hackaton.seriandroid.data.remote.dto.request.SignInRequest
import com.hackaton.seriandroid.data.remote.dto.request.SignUpRequest
import com.hackaton.seriandroid.data.remote.dto.response.FetchVerifyCodeResponse
import com.hackaton.seriandroid.data.remote.dto.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {

    @POST("api/v1/authorize/email")
    suspend fun postAuthorizeEmail(
        @Body email: String
    )

    @GET("api/v1/authorize/email/{number}")
    suspend fun fetchVerifyCode(
        @Path("number") number: Int
    ): FetchVerifyCodeResponse

    @POST("api/v1/account/login/login")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    )

    @POST("api/v1/account/signup")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): SignUpResponse

    @POST("api/v1/account/policy/login/email")
    suspend fun checkSignInEmailPolicy(
        @Body email: String
    )

    @POST("api/v1/account/policy/login/password")
    suspend fun checkSignInPasswordPolicy(
        @Body password: String
    )

    @POST("api/v1/account/policy/signup/email")
    suspend fun checkSignUpEmailPolicy(
        @Body email: String
    )

    @GET("api/v1/account/policy/signup/nickname/{nickname}")
    suspend fun checkSignUpNicknamePolicy(
        @Path("nickname") nickname: String
    )

    @POST("api/v1/account/policy/signup/password")
    suspend fun checkSignUpPasswordPolicy(
        @Body password: String
    )
}