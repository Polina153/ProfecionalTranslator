package com.example.profecionalcoursetranslator.model.datasource

interface DataSource<T> {

    suspend fun getData(word: String): T
}