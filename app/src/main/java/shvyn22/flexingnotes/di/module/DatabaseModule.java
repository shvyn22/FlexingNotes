package shvyn22.flexingnotes.di.module;

import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import shvyn22.flexingnotes.data.local.AppDatabase;
import shvyn22.flexingnotes.data.local.dao.NoteDao;
import shvyn22.flexingnotes.data.local.dao.TodoDao;
import shvyn22.flexingnotes.util.Constants;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    public static AppDatabase provideDatabase(
        Application app
    ) {
        return Room
            .databaseBuilder(
                app,
                AppDatabase.class,
                Constants.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build();
    }

    @Singleton
    @Provides
    public static NoteDao provideNoteDao(
        AppDatabase db
    ) {
        return db.noteDao();
    }

    @Singleton
    @Provides
    public static TodoDao provideTodoDao(
        AppDatabase db
    ) {
        return db.todoDao();
    }
}