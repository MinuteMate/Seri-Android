package com.hackaton.seriandroid.di.module

import com.hackaton.seriandroid.data.local.storage.AuthDataStorage
import com.hackaton.seriandroid.data.local.storage.AuthDataStorageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    abstract fun provideAuthDataStorage(
        authDataStorageImpl: AuthDataStorageImpl
    ): AuthDataStorage
}