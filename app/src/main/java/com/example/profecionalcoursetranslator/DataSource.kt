package com.example.profecionalcoursetranslator

import io.reactivex.Observable

interface DataSource<T> {

    fun getData(word: String): Observable<T>
}