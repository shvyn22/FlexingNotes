package shvyn22.flexingnotes.data.local;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import shvyn22.flexingnotes.data.local.model.Todo;

public class TypeConverter {
    @androidx.room.TypeConverter
    public static String fromTodoList(ArrayList<Todo> todos) {
        return new Gson().toJson(todos);
    }

    @androidx.room.TypeConverter
    public static ArrayList<Todo> fromString(String todos) {
        Type listType = new TypeToken<ArrayList<Todo>>() {}.getType();
        return new Gson().fromJson(todos, listType);
    }
}
