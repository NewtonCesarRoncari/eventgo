package com.newton.eventgo

import android.app.Application
import com.newton.eventgo.extensions.TypefaceUtil

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val typefaceUtil = TypefaceUtil()
        typefaceUtil.overrideFonts(
            this,
            "SERIF",
            "fonts/OpenSans-Semibold.ttf"
        )
    }
}