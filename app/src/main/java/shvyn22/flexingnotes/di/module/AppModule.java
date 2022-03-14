package shvyn22.flexingnotes.di.module;

import dagger.Module;

@Module(includes = {
    DatabaseModule.class,
    RepositoryModule.class,
    ViewModelModule.class}
)
public class AppModule {}
