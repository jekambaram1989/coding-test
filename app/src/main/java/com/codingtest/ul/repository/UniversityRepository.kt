package com.codingtest.ul.repository


import com.codingtest.ul.db.DatabaseHelper
import com.codingtest.ul.db.UniversityDao
import com.codingtest.ul.network.BaseApi
import com.codingtest.ul.network.SafeApiRequest
import com.codingtest.ul.network.response.University
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UniversityRepository @Inject constructor(
    private val api: BaseApi,
) : SafeApiRequest(), DatabaseHelper {

    @Inject
    lateinit var universityDao: UniversityDao

    suspend fun getNetworkUniversities(): List<University> {
        return apiRequest {
            api.getUniversityList("search?country=United+Kingdom")
        }
    }

    override suspend fun insertUniversities(universityList: List<University>) {
        withContext(Dispatchers.IO) {
            universityDao.insertUniversities(universityList)
        }
    }

    override suspend fun getLocalUniversities(): List<University> {
        return withContext(Dispatchers.IO) {
            universityDao.getUniversities()
        }
    }

    override suspend fun deleteAll() {
        return withContext(Dispatchers.IO) {
            universityDao.deleteAll()
        }
    }
}