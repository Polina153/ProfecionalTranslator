import com.example.profecionalcoursetranslator.di.DiConstants.Companion.NAME_LOCAL
import com.example.profecionalcoursetranslator.di.DiConstants.Companion.NAME_REMOTE
import com.example.profecionalcoursetranslator.interactor.MainInteractor
import com.example.profecionalcoursetranslator.model.data.DataModel
import com.example.profecionalcoursetranslator.model.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}
