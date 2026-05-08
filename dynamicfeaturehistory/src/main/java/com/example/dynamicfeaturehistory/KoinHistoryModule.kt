package com.example.dynamicfeaturehistory

import com.example.core.FeatureNavigator
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val featureModule = module {
    // Используем single или factory, передаем androidContext()
    single<FeatureNavigator> { FeatureNavigatorImpl(androidContext()) }
}