package com.example.profecionalcoursetranslator.model.local

import com.example.profecionalcoursetranslator.model.repository.DataSource
import com.example.profecionalcoursetranslator.model.data.DataModel
import io.reactivex.Observable

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented") // To change body of created functions use File
        // | Settings | File Templates.
    }

}