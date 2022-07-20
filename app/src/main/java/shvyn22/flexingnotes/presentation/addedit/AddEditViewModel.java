package shvyn22.flexingnotes.presentation.addedit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.schedulers.Schedulers;
import shvyn22.flexingnotes.data.local.model.Note;
import shvyn22.flexingnotes.data.local.model.Todo;
import shvyn22.flexingnotes.repository.local.note.NoteRepository;
import shvyn22.flexingnotes.repository.local.todo.TodoRepository;

public class AddEditViewModel extends ViewModel {

    private final NoteRepository noteRepo;
    private final TodoRepository todoRepo;

    @Inject
    public AddEditViewModel(
        NoteRepository noteRepo,
        TodoRepository todoRepo
    ) {
        this.noteRepo = noteRepo;
        this.todoRepo = todoRepo;
    }

    public LiveData<Note> getNote(Long id) {
        MutableLiveData<Note> note = new MutableLiveData<>();

        if (id == null || id == -1) {
            noteRepo
                .insertNote(new Note())
                .doOnSuccess(insertedId ->
                    noteRepo
                        .getNote(insertedId)
                        .subscribe(note::postValue)
                )
                .subscribeOn(Schedulers.io())
                .subscribe();
        } else {
            noteRepo
                .getNote(id)
                .subscribeOn(Schedulers.io())
                .subscribe(note::postValue);
        }

        return note;
    }

    public LiveData<List<Todo>> getTodos(Long noteId) {
        MutableLiveData<List<Todo>> todos = new MutableLiveData<>();

        todoRepo
            .getTodos(noteId)
            .subscribeOn(Schedulers.io())
            .subscribe(todos::postValue);

        return todos;
    }

    public void insertTodo(Todo todo) {
        todoRepo.insertTodo(todo);
    }

    public void updateTodo(Todo todo) {
        todoRepo.updateTodo(todo);
    }

    public void deleteTodo(long id) {
        todoRepo.deleteTodo(id);
    }

    public void updateNote(Note note) {
        noteRepo.updateNote(note);
    }

    public void deleteNote(long id) {
        noteRepo.deleteNote(id);
        todoRepo.deleteTodos(id);
    }
}