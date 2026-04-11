package com.example.profecionalcoursetranslator.interactor

import com.example.profecionalcoursetranslator.model.data.DataModel
import com.example.profecionalcoursetranslator.model.repository.Repository
import com.example.profecionalcoursetranslator.model.repository.RepositoryLocal
import com.example.profecionalcoursetranslator.view.AppState
import io.reactivex.Observable


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

    /*override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            repositoryRemote
        } else {
            repositoryLocal
        }.getData(word).map { AppState.Success(it) }
    }
}*/

