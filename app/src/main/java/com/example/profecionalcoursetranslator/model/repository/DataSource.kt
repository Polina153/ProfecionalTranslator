package com.example.profecionalcoursetranslator.model.repository

import io.reactivex.Observable

interface DataSource<T> {

    fun getData(word: String): Observable<T>
}