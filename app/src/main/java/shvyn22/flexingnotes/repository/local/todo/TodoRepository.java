package shvyn22.flexingnotes.repository.local.todo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import shvyn22.flexingnotes.data.local.model.Todo;

public interface TodoRepository {

    Observable<List<Todo>> getTodos(long noteId);

    void insertTodo(Todo todo);

    void updateTodo(Todo todo);

    void deleteTodo(long id);

    void deleteTodos(long noteId);

    void deleteTodos();
}