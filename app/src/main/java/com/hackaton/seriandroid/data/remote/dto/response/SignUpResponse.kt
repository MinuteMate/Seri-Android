package com.hackaton.seriandroid.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("email") val email: String,
    @SerializedName("encodedPassword") val encodedPassword: String,
    @SerializedName("id") val id: Int,
    @SerializedName("nickname") val nickname: String
)
