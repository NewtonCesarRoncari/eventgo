package com.newton.eventgo

import android.app.Application
import com.newton.eventgo.di.modules.repositoryModule
import com.newton.eventgo.di.modules.serviceModule
import com.newton.eventgo.di.modules.viewModelModule
import com.newton.eventgo.extensions.TypefaceUtil
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    serviceModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }

        val typefaceUtil = TypefaceUtil()
        typefaceUtil.overrideFonts(
            this,
            "SERIF",
            "fonts/OpenSans-Semibold.ttf"
        )
    }
}