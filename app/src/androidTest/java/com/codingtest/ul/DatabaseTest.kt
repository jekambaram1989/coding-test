package com.codingtest.ul

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.codingtest.ul.db.Database
import com.codingtest.ul.db.UniversityDao
import com.codingtest.ul.network.response.University
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var database: Database
    private lateinit var universityDao: UniversityDao

    @Before
    fun setUp() {
        database =
            Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                Database::class.java)
                .allowMainThreadQueries()
                .build()
        universityDao = database.universityDao()
    }

    @After
    fun closeDB() {
        database.close()
    }

    @Test
    fun readAndWriteDatabase() = runBlocking {
        val list = ArrayList<University>()
        list.add(University("India", "Anna university"))
        universityDao.insertUniversities(list)
        val universityList = universityDao.getUniversities()
        assertEquals(universityList, list)
    }
}
