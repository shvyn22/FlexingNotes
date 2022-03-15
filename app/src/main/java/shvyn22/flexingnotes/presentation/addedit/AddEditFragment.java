package shvyn22.flexingnotes.presentation.addedit;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
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
    private TodoAdapter adapter;
    private Note note;

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
        adapter = new TodoAdapter();

        Integer id = AddEditFragmentArgs.fromBundle(getArguments()).getId();
        viewModel.getNote(id).observe(getViewLifecycleOwner(), (note) -> {
            this.note = note;
            initUI();
            subscribeObservers();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            saveNote();
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        binding.rvTodos.setAdapter(adapter);

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
                viewModel.deleteTodo(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(binding.rvTodos);

        binding.etTitle.setText(note.title);
        binding.etText.setText(note.text);

        binding.btnAddTodo.setOnClickListener(view -> viewModel.addTodo());

        requireActivity().getOnBackPressedDispatcher().addCallback(
            getViewLifecycleOwner(),
            new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    saveNote();
                }
            }
        );

        setHasOptionsMenu(true);
    }

    private void subscribeObservers() {
        viewModel.getTodos()
            .observe(getViewLifecycleOwner(), (todos) -> adapter.submitList(todos));
    }

    private void saveNote() {
        note.title = binding.etTitle.getText().toString();
        note.text = binding.etText.getText().toString();
        viewModel.insertNote(note);
        Navigation.findNavController(binding.getRoot()).navigateUp();
    }
}
