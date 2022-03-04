package com.hackaton.seriandroid.data.local.datasource

import com.hackaton.seriandroid.data.local.storage.AuthDataStorage
import javax.inject.Inject

class LocalAuthDataSourceImpl @Inject constructor(
    private val authDataStorage: AuthDataStorage
) : LocalAuthDataSource {
    override suspend fun setAccessToken(token: String) {
        authDataStorage.setAccessToken(token)
    }

    override suspend fun fetchAccessToken(): String =
        authDataStorage.fetchAccessToken()

    override suspend fun clearAccessToken() {
        authDataStorage.clearAccessToken()
    }

    override suspend fun setRefreshToken(token: String) {
        authDataStorage.setRefreshToken(token)
    }

    override suspend fun fetchRefreshToken(): String =
        authDataStorage.fetchRefreshToken()

    override suspend fun clearRefreshToken() {
        authDataStorage.clearRefreshToken()
    }

    override suspend fun setId(id: String) {
        authDataStorage.setId(id)
    }

    override suspend fun fetchId(): String =
        authDataStorage.fetchId()

    override suspend fun setPw(pw: String) {
        authDataStorage.setPw(pw)
    }

    override suspend fun fetchPw(): String =
        authDataStorage.fetchPw()
}