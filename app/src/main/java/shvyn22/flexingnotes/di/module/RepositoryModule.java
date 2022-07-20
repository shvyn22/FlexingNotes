package shvyn22.flexingnotes.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import shvyn22.flexingnotes.data.local.dao.NoteDao;
import shvyn22.flexingnotes.data.local.dao.TodoDao;
import shvyn22.flexingnotes.repository.local.note.NoteRepository;
import shvyn22.flexingnotes.repository.local.note.NoteRepositoryImpl;
import shvyn22.flexingnotes.repository.local.todo.TodoRepository;
import shvyn22.flexingnotes.repository.local.todo.TodoRepositoryImpl;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public static NoteRepository provideNoteRepository(
        NoteDao dao
    ) {
        return new NoteRepositoryImpl(dao);
    }

    @Singleton
    @Provides
    public static TodoRepository provideTodoRepository(
        TodoDao dao
    ) {
        return new TodoRepositoryImpl(dao);
    }
}