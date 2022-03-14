package shvyn22.flexingnotes.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import shvyn22.flexingnotes.data.local.dao.NoteDao;
import shvyn22.flexingnotes.data.local.model.Note;

@Database(entities = Note.class, version = 1)
@TypeConverters(TypeConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();
}
