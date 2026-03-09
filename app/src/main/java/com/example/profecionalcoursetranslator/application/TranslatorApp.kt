package com.example.profecionalcoursetranslator.application

import android.app.Application
import com.example.profecionalcoursetranslator.di.application
import com.example.profecionalcoursetranslator.di.mainScreen
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}