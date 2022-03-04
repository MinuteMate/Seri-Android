package com.hackaton.seriandroid.data.remote.dto.request

data class SignUpRequest(
    val emailToken: String,
    val nickname: String,
    val password: String
)
