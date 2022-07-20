package shvyn22.flexingnotes.repository.local.note;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import shvyn22.flexingnotes.data.local.dao.NoteDao;
import shvyn22.flexingnotes.data.local.model.Note;

public class NoteRepositoryImpl implements NoteRepository {

    private final NoteDao dao;

    public NoteRepositoryImpl(
        NoteDao dao
    ) {
        this.dao = dao;
    }

    @Override
    public Observable<List<Note>> getNotes() {
        return dao.getNotes();
    }

    @Override
    public Single<Note> getNote(long id) {
        return dao.getNote(id);
    }

    @Override
    public Single<Long> insertNote(Note note) {
        return dao.insertNote(note);
    }

    @Override
    public void updateNote(Note note) {
        dao
            .updateNote(note)
            .subscribeOn(Schedulers.io())
            .subscribe();
    }

    @Override
    public void deleteNote(long id) {
        dao
            .deleteNote(id)
            .subscribeOn(Schedulers.io())
            .subscribe();
    }

    @Override
    public void deleteNotes() {
        dao
            .deleteNotes()
            .subscribeOn(Schedulers.io())
            .subscribe();
    }
}