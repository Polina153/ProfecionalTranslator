package com.example.profecionalcoursetranslator

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.profecionalcoursetranslator.interactor.MainInteractor
import com.example.profecionalcoursetranslator.utils.parseSearchResults
import com.example.profecionalcoursetranslator.view.AppState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData
    private val TAG = "MainViewModel"

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        Log.d(TAG, "getData: word='$word', isOnline=$isOnline")
        cancelJob()
        // Запускаем корутину для асинхронного доступа к серверу с помощью
        // launch
        // Запускаем корутину в главном потоке
        viewModelCoroutineScope.launch {
            flow {
                // Тяжёлая операция – в фоне
                val result = withContext(Dispatchers.IO) {
                    interactor.getData(word, isOnline)
                }
                // Парсим результат
                val parsed = parseSearchResults(result)
                emit(parsed)
            }
                .onStart {
                    Log.d(TAG, "onStart – отправляем Loading")
                    _mutableLiveData.value = AppState.Loading(null)
                }
                .catch { e ->
                    Log.e(TAG, "Ошибка в Flow", e)
                    _mutableLiveData.value = AppState.Error(e)
                }
                .collect { state ->
                    Log.d(TAG, "collect: $state")
                    _mutableLiveData.value = state
                }
        }
    }

    // Обрабатываем ошибки
    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}
/*
// Инжектим интерактор в конструктор
class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private var appState: AppState? = null

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(doOnSubscribe())
                .subscribeWith(getObserver())
        )
    }

    private fun doOnSubscribe(): (Disposable) -> Unit =
        { liveDataForViewToObserve.value = AppState.Loading(null) }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(state: AppState) {
                appState = parseSearchResults(state)
                liveDataForViewToObserve.value = appState
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }
}*/
