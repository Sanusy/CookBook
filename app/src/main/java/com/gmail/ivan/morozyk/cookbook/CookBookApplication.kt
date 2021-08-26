package com.gmail.ivan.morozyk.cookbook

import android.app.Application
import com.github.kittinunf.fuel.core.FuelManager
import com.gmail.ivan.morozyk.cookbook.di.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CookBookApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        FuelManager.instance.basePath = "https://api.spoonacular.com/"

        startKoin {
            androidLogger(Level.ERROR)//Level.ERROR is used to fix problem of compatibility of Koin library and Kotlin 1.5.0
            androidContext(this@CookBookApplication)
            modules(
                serviceModule
            )
        }
    }
}