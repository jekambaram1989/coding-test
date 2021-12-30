package com.codingtest.ul.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingtest.ul.network.response.University

@Dao
interface UniversityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniversities(university: List<University>)

    @Query("SELECT * FROM university")
    suspend fun getUniversities(): List<University>

    @Query("DELETE FROM university")
    fun deleteAll()
}