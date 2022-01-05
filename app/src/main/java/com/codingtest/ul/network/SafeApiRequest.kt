package com.codingtest.ul.network

import com.codingtest.ul.util.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        return withContext(Dispatchers.IO) {
            val response = call.invoke()
            when (response.isSuccessful) {
                true -> response.body()!!
                false -> throw ApiException("Server not responding")
            }
        }
    }
}