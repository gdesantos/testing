package com.overmind.mywork

import android.app.Application
import com.overmind.mywork.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyWorkApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyWorkApp)
            modules(
                appModule,
                repositoryModule,
                interactorsModule,
                viewModelModule,
                networkModule
            )
        }
    }
}