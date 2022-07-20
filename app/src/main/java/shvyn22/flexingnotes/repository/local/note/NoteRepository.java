package shvyn22.flexingnotes.repository.local.note;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import shvyn22.flexingnotes.data.local.model.Note;

public interface NoteRepository {

    Observable<List<Note>> getNotes();

    Single<Note> getNote(long id);

    Single<Long> insertNote(Note note);

    void updateNote(Note note);

    void deleteNote(long id);

    void deleteNotes();
}