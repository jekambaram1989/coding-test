package com.codingtest.ul.di

import com.codingtest.ul.UniversityViewModelTest
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
@Component(
    modules = [TestAppModule::class]
)

interface TestAppComponent {
    fun inject(universityViewModelTest: UniversityViewModelTest)
}