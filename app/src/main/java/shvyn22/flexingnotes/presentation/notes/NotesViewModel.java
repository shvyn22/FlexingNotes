package shvyn22.flexingnotes.presentation.notes;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import shvyn22.flexingnotes.data.local.model.Note;
import shvyn22.flexingnotes.repository.local.LocalRepository;

public class NotesViewModel extends ViewModel {

    private final LocalRepository<Note> repo;

    @Inject
    public NotesViewModel(
        LocalRepository<Note> repo
    ) {
        this.repo = repo;
    }
}
