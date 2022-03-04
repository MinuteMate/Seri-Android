package com.hackaton.seriandroid.data.local.datasource

interface LocalAuthDataSource {
    suspend fun setAccessToken(token: String)
    suspend fun fetchAccessToken(): String
    suspend fun clearAccessToken()

    suspend fun setRefreshToken(token: String)
    suspend fun fetchRefreshToken(): String
    suspend fun clearRefreshToken()

    suspend fun setId(id: String)
    suspend fun fetchId(): String

    suspend fun setPw(pw: String)
    suspend fun fetchPw(): String
}