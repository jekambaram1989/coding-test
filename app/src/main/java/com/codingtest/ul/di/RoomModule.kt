package com.codingtest.ul.di

import android.content.Context
import androidx.room.Room
import com.codingtest.ul.db.UniversityDao
import com.codingtest.ul.db.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(private val context: Context) {

    companion object {
        private const val DB_NAME = "UniversityDB.db"
    }

    @Singleton
    @Provides
    fun provideUniversityDatabase(): Database {
        return Room.databaseBuilder(context, Database::class.java, DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideUniversityDao(database: Database): UniversityDao {
        return database.universityDao()
    }
}