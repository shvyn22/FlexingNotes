package shvyn22.flexingnotes.presentation.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class MultiViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelFactories;

    @Inject
    public MultiViewModelFactory(
        Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelFactories
    ) {
        this.viewModelFactories = viewModelFactories;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) viewModelFactories.get(modelClass).get();
    }
}