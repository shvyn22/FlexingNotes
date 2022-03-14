package shvyn22.flexingnotes.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import shvyn22.flexingnotes.data.local.dao.NoteDao;
import shvyn22.flexingnotes.data.local.model.Note;
import shvyn22.flexingnotes.repository.local.LocalRepository;
import shvyn22.flexingnotes.repository.local.LocalRepositoryImpl;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public static LocalRepository<Note> provideLocalRepository(
        NoteDao dao
    ) {
        return new LocalRepositoryImpl(dao);
    }
}
