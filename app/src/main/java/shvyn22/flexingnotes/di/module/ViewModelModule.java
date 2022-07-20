package shvyn22.flexingnotes.di.module;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import shvyn22.flexingnotes.di.util.ViewModelKey;
import shvyn22.flexingnotes.presentation.addedit.AddEditViewModel;
import shvyn22.flexingnotes.presentation.notes.NotesViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel.class)
    public abstract ViewModel bindNotesViewModel(NotesViewModel notesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddEditViewModel.class)
    public abstract ViewModel bindAddEditViewModel(AddEditViewModel addEditViewModel);
}