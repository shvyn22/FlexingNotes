package shvyn22.flexingnotes.repository.local;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface LocalRepository<T> {

    Observable<List<T>> getAll();

    Single<T> getItem(int id);

    void insert(T item);

    void update(T item);

    void delete(int id);

    void deleteAll();
}
