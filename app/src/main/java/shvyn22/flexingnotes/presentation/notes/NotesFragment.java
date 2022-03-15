package shvyn22.flexingnotes.presentation.notes;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import shvyn22.flexingnotes.NotesApp;
import shvyn22.flexingnotes.R;
import shvyn22.flexingnotes.data.local.model.Note;
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
    private NotesAdapter adapter;

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
        adapter = new NotesAdapter(this::navigateToAddEdit);

        initUI();
        subscribeObservers();
    }

    private void initUI() {
        binding.rvNotes.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
        ) {
            @Override
            public boolean onMove(
                @NonNull RecyclerView recyclerView,
                @NonNull RecyclerView.ViewHolder viewHolder,
                @NonNull RecyclerView.ViewHolder target
            ) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Note item = adapter.getCurrentList().get(viewHolder.getAdapterPosition());
                viewModel.deleteNote(item.id);
            }
        }).attachToRecyclerView(binding.rvNotes);

        binding.fabAdd.setOnClickListener(view -> navigateToAddEdit(-1));

        setHasOptionsMenu(true);
    }

    private void subscribeObservers() {
        viewModel.getNotes().observe(getViewLifecycleOwner(), notes -> {
            if (notes != null) adapter.submitList(notes);
        });
    }

    private void navigateToAddEdit(Integer id) {
        Navigation
            .findNavController(binding.getRoot())
            .navigate(NotesFragmentDirections
                .actionNotesFragmentToAddEditFragment(id)
            );
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_delete)
            viewModel.deleteAll();
        return super.onOptionsItemSelected(item);
    }
}
