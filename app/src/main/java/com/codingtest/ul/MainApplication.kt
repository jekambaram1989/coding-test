package com.codingtest.ul

import android.app.Application
import com.codingtest.ul.di.AppComponent
import com.codingtest.ul.di.AppModule
import com.codingtest.ul.di.DaggerAppComponent
import com.codingtest.ul.di.RoomModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class MainApplication : Application(), HasAndroidInjector {

    @Inject
    public lateinit var mInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        initializeDaggerComponent()
    }

    open fun initializeDaggerComponent(): AppComponent {
        val component: AppComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .roomModule(RoomModule(this))
            .build()
        component.inject(this)
        return component
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return mInjector
    }
}
