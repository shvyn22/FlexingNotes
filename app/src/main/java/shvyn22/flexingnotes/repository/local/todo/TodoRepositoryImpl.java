package shvyn22.flexingnotes.repository.local.todo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import shvyn22.flexingnotes.data.local.dao.TodoDao;
import shvyn22.flexingnotes.data.local.model.Todo;

public class TodoRepositoryImpl implements TodoRepository {

    private final TodoDao dao;

    public TodoRepositoryImpl(
        TodoDao dao
    ) {
        this.dao = dao;
    }

    @Override
    public Observable<List<Todo>> getTodos(long noteId) {
        return dao.getTodos(noteId);
    }

    @Override
    public void insertTodo(Todo todo) {
        dao
            .insertTodo(todo)
            .subscribeOn(Schedulers.io())
            .subscribe();
    }

    @Override
    public void updateTodo(Todo todo) {
        dao
            .updateTodo(todo)
            .subscribeOn(Schedulers.io())
            .subscribe();
    }

    @Override
    public void deleteTodo(long id) {
        dao
            .deleteTodo(id)
            .subscribeOn(Schedulers.io())
            .subscribe();
    }

    @Override
    public void deleteTodos(long noteId) {
        dao
            .deleteTodos(noteId)
            .subscribeOn(Schedulers.io())
            .subscribe();
    }

    @Override
    public void deleteTodos() {
        dao
            .deleteTodos()
            .subscribeOn(Schedulers.io())
            .subscribe();
    }
}