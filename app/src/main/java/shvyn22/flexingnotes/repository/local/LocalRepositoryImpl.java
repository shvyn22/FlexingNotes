package shvyn22.flexingnotes.repository.local;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import shvyn22.flexingnotes.data.local.dao.NoteDao;
import shvyn22.flexingnotes.data.local.model.Note;

public class LocalRepositoryImpl implements LocalRepository<Note> {

    private final NoteDao dao;

    public LocalRepositoryImpl(NoteDao dao) {
        this.dao = dao;
    }

    @Override
    public Observable<List<Note>> getAll() {
        return dao.getNotes();
    }

    @Override
    public Single<Note> getItem(int id) {
        return dao.getNote(id);
    }

    @Override
    public void insert(Note item) {
        dao.insertNote(item)
            .subscribeOn(Schedulers.io())
            .subscribe();
    }

    @Override
    public void delete(int id) {
        dao.deleteNote(id)
            .subscribeOn(Schedulers.io())
            .subscribe();
    }

    @Override
    public void deleteAll() {
        dao.deleteAll()
            .subscribeOn(Schedulers.io())
            .subscribe();
    }
}
