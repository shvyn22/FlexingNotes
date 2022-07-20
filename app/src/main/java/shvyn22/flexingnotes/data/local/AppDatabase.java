package shvyn22.flexingnotes.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import shvyn22.flexingnotes.data.local.dao.NoteDao;
import shvyn22.flexingnotes.data.local.dao.TodoDao;
import shvyn22.flexingnotes.data.local.model.Note;
import shvyn22.flexingnotes.data.local.model.Todo;

@Database(
    entities = {Note.class, Todo.class},
    version = 3
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    public abstract TodoDao todoDao();
}