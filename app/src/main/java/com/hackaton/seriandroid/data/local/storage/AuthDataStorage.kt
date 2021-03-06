package com.hackaton.seriandroid.data.local.storage

interface AuthDataStorage {
    fun setAccessToken(token: String)
    fun fetchAccessToken(): String
    fun clearAccessToken()

    fun setRefreshToken(token: String)
    fun fetchRefreshToken(): String
    fun clearRefreshToken()

    fun setId(id: String)
    fun fetchId(): String

    fun setPw(pw: String)
    fun fetchPw(): String
}