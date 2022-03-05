package com.hackaton.seriandroid.data.remote.network

import com.google.gson.annotations.SerializedName


data class ErrorResponse(
    @SerializedName("message") val message: String
)
