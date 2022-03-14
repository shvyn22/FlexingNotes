package shvyn22.flexingnotes.presentation.addedit;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import shvyn22.flexingnotes.NotesApp;
import shvyn22.flexingnotes.R;
import shvyn22.flexingnotes.databinding.FragmentAddEditBinding;
import shvyn22.flexingnotes.presentation.util.MultiViewModelFactory;

public class AddEditFragment extends Fragment {

    public AddEditFragment() {
        super(R.layout.fragment_add_edit);
    }

    @Inject
    MultiViewModelFactory viewModelFactory;

    private AddEditViewModel viewModel;
    private FragmentAddEditBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((NotesApp) context.getApplicationContext()).singletonComponent.inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity(), viewModelFactory)
            .get(AddEditViewModel.class);
        binding = FragmentAddEditBinding.bind(view);
    }
}
