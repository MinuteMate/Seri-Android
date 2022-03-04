package com.hackaton.seriandroid.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class FetchVerifyCodeResponse(
    @SerializedName("token") val token: String
)
