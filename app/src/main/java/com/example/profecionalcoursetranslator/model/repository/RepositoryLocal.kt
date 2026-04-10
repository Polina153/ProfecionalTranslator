package com.example.profecionalcoursetranslator.model.repository

import com.example.profecionalcoursetranslator.view.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}
