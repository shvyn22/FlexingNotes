package shvyn22.flexingnotes.presentation.notes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.schedulers.Schedulers;
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

    public LiveData<List<Note>> getNotes() {
        MutableLiveData<List<Note>> notes = new MutableLiveData<>();

        repo
            .getAll()
            .subscribeOn(Schedulers.io())
            .subscribe(notes::postValue);

        return notes;
    }

    public void deleteNote(int id) {
        repo.delete(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }
}
