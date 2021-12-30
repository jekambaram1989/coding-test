package com.codingtest.ul.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingtest.ul.network.response.University

@Database(entities = [University::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun universityDao(): UniversityDao
}