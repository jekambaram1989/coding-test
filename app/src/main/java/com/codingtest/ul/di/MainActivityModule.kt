package com.codingtest.ul.di

import com.codingtest.ul.ui.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragmentInjector(): MainFragment
}