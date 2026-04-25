package com.example.repository

import com.example.model.DataModel

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    // Репозиторий возвращает данные, используя dataSource (локальный или
    // внешний)
    /*override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }*/
    // Добавляем suspend
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

}