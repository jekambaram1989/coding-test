package com.codingtest.ul.di

import com.codingtest.ul.MainApplication
import com.codingtest.ul.db.UniversityDao
import com.codingtest.ul.network.NetworkConnectionInterceptor
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules =
    [AndroidInjectionModule::class,
        MainActivityModule::class,
        AppModule::class,
        RoomModule::class]
)
interface AppComponent {
    fun inject(application: MainApplication)
    val networkConnectionInterceptor: NetworkConnectionInterceptor
    val universityDao: UniversityDao
}