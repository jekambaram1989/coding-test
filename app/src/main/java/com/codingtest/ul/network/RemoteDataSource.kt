package com.codingtest.ul.network

import com.codingtest.ul.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {

    @Inject
    lateinit var networkConnectionInterceptor: NetworkConnectionInterceptor

    fun <T> buildApi(api: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(getClient(networkConnectionInterceptor))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

    private fun getClient(networkConnectionInterceptor: NetworkConnectionInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.header("Accept", "application/json")
                }.build())
            }
            .addInterceptor(networkConnectionInterceptor)
            .build()
    }

}
