package com.example.profecionalcoursetranslator.view

import com.example.core.Interactor
import com.example.model.AppState
import com.example.model.DataModel
import com.example.repository.Repository
import com.example.repository.RepositoryLocal

open class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        // Теперь полученное слово мы сохраняем в БД. Сделать это нужно именно
        // здесь, в соответствии с принципами чистой архитектуры: интерактор
        // обращается к репозиторию
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState

        /* return AppState.Success(
             if (fromRemoteSource) {
                 repositoryRemote
             } else {
                 repositoryLocal
             }.getData(word)
         )*/
    }
}