package com.codingtest.ul.network

import com.codingtest.ul.network.response.University
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface BaseApi {
    @GET
    suspend fun getUniversityList(@Url country: String): Response<List<University>>
}