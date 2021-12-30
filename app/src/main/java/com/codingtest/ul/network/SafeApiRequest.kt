package com.codingtest.ul.network

import com.codingtest.ul.util.NetworkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): NetworkState {
        return withContext(Dispatchers.IO) {
            val response = call.invoke()
            when (response.isSuccessful) {
                true -> NetworkState.success(response.body()!!)
                false -> NetworkState.error("Server not responding")
            }
        }
    }
}