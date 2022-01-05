package com.codingtest.ul.di

import android.content.Context
import com.codingtest.ul.network.BaseApi
import com.codingtest.ul.network.NetworkConnectionInterceptor
import com.codingtest.ul.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideBaseApi(remoteDataSource: RemoteDataSource): BaseApi {
        return remoteDataSource.buildApi(BaseApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkConnectionInterceptor(): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(context)
    }

}