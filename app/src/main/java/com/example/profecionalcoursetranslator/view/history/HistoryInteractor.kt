package com.example.profecionalcoursetranslator.view.history

import com.example.profecionalcoursetranslator.interactor.Interactor
import com.example.profecionalcoursetranslator.model.data.DataModel
import com.example.profecionalcoursetranslator.model.repository.Repository
import com.example.profecionalcoursetranslator.model.repository.RepositoryLocal
import com.example.profecionalcoursetranslator.view.AppState

// Класс мало чем отличается от интерактора, который мы уже описывали
class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState{
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}