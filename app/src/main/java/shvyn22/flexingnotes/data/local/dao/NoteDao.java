package shvyn22.flexingnotes.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import shvyn22.flexingnotes.data.local.model.Note;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM Note ORDER BY id DESC")
    Observable<List<Note>> getNotes();

    @Query("SELECT * FROM Note WHERE id = :id")
    Single<Note> getNote(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertNote(Note note);

    @Update
    Completable updateNote(Note note);

    @Query("DELETE FROM Note WHERE id = :id")
    Completable deleteNote(long id);

    @Query("DELETE FROM Note")
    Completable deleteNotes();
}