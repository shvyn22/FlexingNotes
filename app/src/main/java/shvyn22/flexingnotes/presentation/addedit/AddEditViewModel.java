package shvyn22.flexingnotes.presentation.addedit;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.rxjava3.schedulers.Schedulers;
import shvyn22.flexingnotes.data.local.model.Note;
import shvyn22.flexingnotes.data.local.model.Todo;
import shvyn22.flexingnotes.repository.local.LocalRepository;

public class AddEditViewModel extends ViewModel {

    private final LocalRepository<Note> repo;

    @Inject
    public AddEditViewModel(
        LocalRepository<Note> repo
    ) {
        this.repo = repo;
    }

    private final MutableLiveData<ArrayList<Todo>> todos = new MutableLiveData<>();

    public LiveData<ArrayList<Todo>> getTodos() {
        return todos;
    }

    public LiveData<Note> getNote(Integer id) {
        MutableLiveData<Note> note = new MutableLiveData<>();

        if (id == null || id == -1) {
            note.postValue(new Note());
            todos.postValue(new ArrayList<>());
        } else {
            repo
                .getItem(id)
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    note.postValue(it);
                    todos.postValue(it.todos);
                });
        }

        return note;
    }

    public void addTodo() {
        if (todos.getValue() != null) {
            todos.getValue().add(new Todo());
            todos.setValue(todos.getValue());
        }
    }

    public void deleteTodo(int pos) {
        if (todos.getValue() != null) {
            todos.getValue().remove(pos);
            todos.setValue(todos.getValue());
        }
    }

    public void insertNote(Note note) {
        note.todos = todos.getValue();
        repo.insert(note);
    }
}
