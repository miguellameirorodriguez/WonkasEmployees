package com.miguel4meiro.wonkasemployees

import android.app.Application
import com.miguel4meiro.wonkasemployees.configuration.detailModule
import com.miguel4meiro.wonkasemployees.configuration.listModule
import com.miguel4meiro.wonkasemployees.configuration.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                serviceModule,
                listModule,
                detailModule
            )
        }
    }
}