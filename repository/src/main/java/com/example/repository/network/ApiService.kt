package com.example.repository.network

import com.example.model.DataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    //fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>

}