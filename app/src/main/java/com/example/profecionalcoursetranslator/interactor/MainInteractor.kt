package com.example.profecionalcoursetranslator.interactor

import com.example.profecionalcoursetranslator.di.DiConstants.Companion.NAME_LOCAL
import com.example.profecionalcoursetranslator.di.DiConstants.Companion.NAME_REMOTE
import com.example.profecionalcoursetranslator.view.AppState
import com.example.profecionalcoursetranslator.model.data.DataModel
import com.example.profecionalcoursetranslator.model.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

/*class MainInteractor(
    // Снабжаем интерактор репозиторием для получения локальных или внешних
    // данных
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {*/
open class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val repositoryRemote: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {

    // Интерактор лишь запрашивает у репозитория данные, детали имплементации
    // интерактору неизвестны
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            repositoryRemote
            // remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            repositoryLocal
            //localRepository.getData(word).map { AppState.Success(it) }
        }.getData(word).map { AppState.Success(it) }
    }
}

