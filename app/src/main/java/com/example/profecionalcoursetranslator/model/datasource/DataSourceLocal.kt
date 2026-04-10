package com.example.profecionalcoursetranslator.model.datasource

import com.example.profecionalcoursetranslator.view.AppState


interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}