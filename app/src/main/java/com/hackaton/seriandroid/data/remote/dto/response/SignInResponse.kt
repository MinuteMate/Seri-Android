package com.hackaton.seriandroid.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("loginToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String
)