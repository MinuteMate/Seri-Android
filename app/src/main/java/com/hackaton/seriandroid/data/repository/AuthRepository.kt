package com.hackaton.seriandroid.data.repository

import com.hackaton.seriandroid.data.local.datasource.LocalAuthDataSource
import com.hackaton.seriandroid.data.remote.datasource.RemoteAuthDataSource
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteAuthDataSource: RemoteAuthDataSource,
    private val localAuthDataSource: LocalAuthDataSource
) {

}