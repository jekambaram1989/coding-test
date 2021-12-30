package com.codingtest.ul.db

import com.codingtest.ul.network.response.University

interface DatabaseHelper {
    suspend fun insertUniversities(universityList: List<University>)
    suspend fun getUniversities(): List<University>
    suspend fun deleteAll()
}