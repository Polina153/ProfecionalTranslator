package com.example.profecionalcoursetranslator.di

import androidx.room.Room
import com.example.profecionalcoursetranslator.MainViewModel
import com.example.profecionalcoursetranslator.interactor.MainInteractor
import com.example.profecionalcoursetranslator.model.data.DataModel
import com.example.profecionalcoursetranslator.model.datasource.RetrofitImplementation
import com.example.profecionalcoursetranslator.model.datasource.RoomDataBaseImplementation
import com.example.profecionalcoursetranslator.model.repository.Repository
import com.example.profecionalcoursetranslator.model.repository.RepositoryImplementation
import com.example.profecionalcoursetranslator.model.repository.RepositoryImplementationLocal
import com.example.profecionalcoursetranslator.model.repository.RepositoryLocal
import com.example.profecionalcoursetranslator.room.HistoryDataBase
import com.example.profecionalcoursetranslator.view.history.HistoryInteractor
import com.example.profecionalcoursetranslator.view.history.HistoryViewModel
import org.koin.dsl.module

// Для удобства создадим две переменные: в одной находятся зависимости,
// используемые во всём приложении, во второй - зависимости конкретного экрана
val applicationModule = module {
    /*// single указывает, что БД должна быть в единственном экземпляре
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    // Получаем DAO
    single { get<HistoryDataBase>().historyDao() }*/
    // Функция single сообщает Koin, что эта зависимость должна храниться
    // в виде синглтона (в Dagger есть похожая аннотация)
    // Аннотация named выполняет аналогичную Dagger функцию

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }


    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(
            RoomDataBaseImplementation(get())
        )
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}
/*// Функция factory сообщает Koin, что эту зависимость нужно создавать каждый
// раз заново, что как раз подходит для Activity и её компонентов.
val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}*/