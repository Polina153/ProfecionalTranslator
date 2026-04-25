package com.example.repository

// Репозиторий представляет собой слой получения и хранения данных, которые он
// передаёт интерактору
interface Repository<T> {

    //fun getData(word: String): Observable<T>
    // Добавляем suspend
    suspend fun getData(word: String): T
}