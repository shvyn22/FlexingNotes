package shvyn22.flexingnotes.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import shvyn22.flexingnotes.data.local.model.Todo;

@Dao
public interface TodoDao {

    @Query("SELECT * FROM Todo WHERE noteId = :noteId")
    Observable<List<Todo>> getTodos(long noteId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertTodo(Todo todo);

    @Update
    Completable updateTodo(Todo todo);

    @Query("DELETE FROM Todo WHERE id = :id")
    Completable deleteTodo(long id);

    @Query("DELETE FROM Todo WHERE noteId = :noteId")
    Completable deleteTodos(long noteId);

    @Query("DELETE FROM Todo")
    Completable deleteTodos();
}