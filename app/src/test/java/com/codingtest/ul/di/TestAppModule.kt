package com.codingtest.ul.di

import android.content.Context
import com.codingtest.ul.network.BaseApi
import com.codingtest.ul.network.RemoteDataSource
import com.codingtest.ul.repository.UniversityRepository
import com.codingtest.ul.ui.viewmodel.UniversityViewModel
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

@Module
class TestAppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideBaseApi(remoteDataSource: RemoteDataSource): BaseApi {
        return remoteDataSource.buildApi(BaseApi::class.java, context)
    }

    @Singleton
    @Provides
    fun provideUniversityRepository(): UniversityRepository {
        return Mockito.mock(UniversityRepository::class.java)
    }

    @Singleton
    @Provides
    fun provideUniversityViewModel(): UniversityViewModel {
        return Mockito.mock(UniversityViewModel::class.java)
    }
}