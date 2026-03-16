package com.example.profecionalcoursetranslator.di

import android.app.Application
import android.content.Context
import org.junit.Assert.assertNotNull
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.get
import kotlin.test.Test

class DependencyGraphTest: KoinTest {

 /*   @Test
    fun testFunc() {
        // Объявите модули
        val mockModule = module {
            single(name = "service_name") { SampleService() }
        }

        startKoin(listOf(mockModule))

        val service: SampleService = get("service_name")

        assertNotNull(service)
    }

    @Test
    fun checkDependencyGraph() {
        // Определите через override все зависимости
        val mockApplication = module(override = true) {
            single { mock(Application::class.java) }
            single { mock(Context::class.java) }
        }

        // Получите все модули
        val moduleList = appModules + activityModules + mockApplication
        // Проверьте список
        checkModules(moduleList)
    }*/
}