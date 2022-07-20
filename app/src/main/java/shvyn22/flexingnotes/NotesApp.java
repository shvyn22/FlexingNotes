package shvyn22.flexingnotes;

import android.app.Application;

import shvyn22.flexingnotes.di.component.DaggerSingletonComponent;
import shvyn22.flexingnotes.di.component.SingletonComponent;

public class NotesApp extends Application {

    public SingletonComponent singletonComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        singletonComponent = DaggerSingletonComponent.factory().create(this);
    }
}