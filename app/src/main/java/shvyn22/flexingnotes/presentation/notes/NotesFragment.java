package shvyn22.flexingnotes.presentation.notes;

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
import shvyn22.flexingnotes.databinding.FragmentNotesBinding;
import shvyn22.flexingnotes.presentation.util.MultiViewModelFactory;

public class NotesFragment extends Fragment {

    public NotesFragment() {
        super(R.layout.fragment_notes);
    }

    @Inject
    MultiViewModelFactory viewModelFactory;

    private NotesViewModel viewModel;
    private FragmentNotesBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((NotesApp) context.getApplicationContext()).singletonComponent.inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity(), viewModelFactory)
            .get(NotesViewModel.class);
        binding = FragmentNotesBinding.bind(view);
    }
}
