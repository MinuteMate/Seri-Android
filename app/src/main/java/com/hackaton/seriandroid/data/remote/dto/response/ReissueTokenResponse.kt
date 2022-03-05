package com.hackaton.seriandroid.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class ReissueTokenResponse(
    @SerializedName("loginToken") val loginToken: String,
    @SerializedName("refreshToken") val refreshToken: String
)
