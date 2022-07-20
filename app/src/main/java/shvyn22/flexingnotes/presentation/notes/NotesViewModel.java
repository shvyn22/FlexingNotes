package shvyn22.flexingnotes.presentation.notes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.schedulers.Schedulers;
import shvyn22.flexingnotes.data.local.model.Note;
import shvyn22.flexingnotes.repository.local.note.NoteRepository;
import shvyn22.flexingnotes.repository.local.todo.TodoRepository;

public class NotesViewModel extends ViewModel {

    private final NoteRepository noteRepo;
    private final TodoRepository todoRepo;

    @Inject
    public NotesViewModel(
        NoteRepository noteRepo,
        TodoRepository todoRepo
    ) {
        this.noteRepo = noteRepo;
        this.todoRepo = todoRepo;
    }

    public LiveData<List<Note>> getNotes() {
        MutableLiveData<List<Note>> notes = new MutableLiveData<>();

        noteRepo
            .getNotes()
            .subscribeOn(Schedulers.io())
            .subscribe(notes::postValue);

        return notes;
    }

    public void deleteNote(long id) {
        noteRepo.deleteNote(id);
        todoRepo.deleteTodos(id);
    }

    public void deleteNotes() {
        noteRepo.deleteNotes();
        todoRepo.deleteTodos();
    }
}