package com.example.profecionalcoursetranslator.application

import android.app.Application
import com.example.profecionalcoursetranslator.di.applicationModule
import com.example.profecionalcoursetranslator.di.historyScreen
import com.example.profecionalcoursetranslator.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(applicationModule, mainScreen, historyScreen))
        }
    }
}