package com.example.profecionalcoursetranslator

import io.reactivex.Observable

// Нижний уровень. View знает о контексте и фреймворке
interface View {
    // View имеет только один метод, в который приходит некое состояние приложения
    fun renderData(appState: AppState)

}


// Репозиторий представляет собой слой получения и хранения данных, которые он
// передаёт интерактору
interface Repository<T> {

    fun getData(word: String): Observable<T>
}
// Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSource<T> {

    fun getData(word: String): Observable<T>
}
