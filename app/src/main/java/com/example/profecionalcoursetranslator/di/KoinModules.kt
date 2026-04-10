package com.example.profecionalcoursetranslator.di

import androidx.room.Room
import com.example.profecionalcoursetranslator.MainViewModel
import com.example.profecionalcoursetranslator.di.DiConstants.Companion.NAME_LOCAL
import com.example.profecionalcoursetranslator.di.DiConstants.Companion.NAME_REMOTE
import com.example.profecionalcoursetranslator.interactor.MainInteractor
import com.example.profecionalcoursetranslator.model.data.DataModel
import com.example.profecionalcoursetranslator.model.datasource.RoomDataBaseImplementation
import com.example.profecionalcoursetranslator.model.datasource.RetrofitImplementation
import com.example.profecionalcoursetranslator.model.repository.Repository
import com.example.profecionalcoursetranslator.model.repository.RepositoryImplementation
import com.example.profecionalcoursetranslator.room.HistoryDataBase
import com.example.profecionalcoursetranslator.view.history.HistoryInteractor
import com.example.profecionalcoursetranslator.view.history.HistoryViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

// Для удобства создадим две переменные: в одной находятся зависимости,
// используемые во всём приложении, во второй - зависимости конкретного экрана
val application = module {
    // single указывает, что БД должна быть в единственном экземпляре
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    // Получаем DAO
    single { get<HistoryDataBase>().historyDao() }
    // Функция single сообщает Koin, что эта зависимость должна храниться
    // в виде синглтона (в Dagger есть похожая аннотация)
    // Аннотация named выполняет аналогичную Dagger функцию
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) { RepositoryImplementation(
        /*RoomDataBaseImplementation()*/
        RetrofitImplementation()
    ) }
}
/*// Функция factory сообщает Koin, что эту зависимость нужно создавать каждый
// раз заново, что как раз подходит для Activity и её компонентов.
val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}*/

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(RepositoryImplementation(RetrofitImplementation())) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}