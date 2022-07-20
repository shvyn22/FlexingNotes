package shvyn22.flexingnotes.data.local.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id = 0;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "isCompleted")
    public boolean isCompleted;

    @ColumnInfo(name = "noteId")
    public long noteId;

    public Todo() {
        this.text = "";
        this.isCompleted = false;
    }

    public Todo(
        String text,
        boolean isCompleted,
        long noteId
    ) {
        this.text = text;
        this.isCompleted = isCompleted;
        this.noteId = noteId;
    }
}