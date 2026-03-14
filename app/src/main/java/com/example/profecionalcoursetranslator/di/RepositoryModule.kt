import com.example.profecionalcoursetranslator.di.DiConstants.Companion.NAME_LOCAL
import com.example.profecionalcoursetranslator.di.DiConstants.Companion.NAME_REMOTE
import com.example.profecionalcoursetranslator.model.data.DataModel
import com.example.profecionalcoursetranslator.model.local.RoomDataBaseImplementation
import com.example.profecionalcoursetranslator.model.network.RetrofitImplementation
import com.example.profecionalcoursetranslator.model.repository.DataSource
import com.example.profecionalcoursetranslator.model.repository.Repository
import com.example.profecionalcoursetranslator.model.repository.RepositoryImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<DataModel>> =
        RoomDataBaseImplementation()
}
