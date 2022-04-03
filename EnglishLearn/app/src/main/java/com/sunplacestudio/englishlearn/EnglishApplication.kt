package com.sunplacestudio.englishlearn

import android.app.Application
import com.sunplacestudio.englishlearn.di.dataBaseFactory
import com.sunplacestudio.englishlearn.di.modelsFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EnglishApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EnglishApplication)
            modules(listOf(dataBaseFactory, modelsFactory))
        }
    }
}