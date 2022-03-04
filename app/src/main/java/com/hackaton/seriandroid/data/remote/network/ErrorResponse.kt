package com.hackaton.seriandroid.data.remote.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    val errorMessage: String
)
