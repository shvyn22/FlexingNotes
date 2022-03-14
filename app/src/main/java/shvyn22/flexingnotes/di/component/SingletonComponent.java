package shvyn22.flexingnotes.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import shvyn22.flexingnotes.di.module.AppModule;
import shvyn22.flexingnotes.presentation.addedit.AddEditFragment;
import shvyn22.flexingnotes.presentation.notes.NotesFragment;

@Singleton
@Component(modules = AppModule.class)
public interface SingletonComponent {

    void inject(NotesFragment notesFragment);

    void inject(AddEditFragment addEditFragment);

    @Component.Factory
    interface Factory {
        SingletonComponent create(
            @BindsInstance Application application
        );
    }
}
