package com.codingtest.ul.repository


import com.codingtest.ul.db.DatabaseHelper
import com.codingtest.ul.db.UniversityDao
import com.codingtest.ul.network.SafeApiRequest
import com.codingtest.ul.network.BaseApi
import com.codingtest.ul.network.response.University
import com.codingtest.ul.util.NetworkState
import javax.inject.Inject

class UniversityRepository @Inject constructor(
    private val api: BaseApi,
) : SafeApiRequest(), DatabaseHelper {

    @Inject
    lateinit var universityDao: UniversityDao

    suspend fun getUniversityListNetwork(): NetworkState {
        return apiRequest {
            api.getUniversityList("search?country=United+Kingdom")
        }
    }

    override suspend fun insertUniversities(universityList: List<University>) {
        universityDao.insertUniversities(universityList)
    }

    override suspend fun getUniversities() =
        universityDao.getUniversities()

    override suspend fun deleteAll() {
        universityDao.deleteAll()
    }

}