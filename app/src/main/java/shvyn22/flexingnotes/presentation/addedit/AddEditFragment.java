package shvyn22.flexingnotes.presentation.addedit;

import static shvyn22.flexingnotes.util.Constants.REQUEST_TODO;
import static shvyn22.flexingnotes.util.Constants.RESULT_TODO_COMPLETENESS;
import static shvyn22.flexingnotes.util.Constants.RESULT_TODO_ID;
import static shvyn22.flexingnotes.util.Constants.RESULT_TODO_TEXT;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import shvyn22.flexingnotes.NotesApp;
import shvyn22.flexingnotes.R;
import shvyn22.flexingnotes.data.local.model.Note;
import shvyn22.flexingnotes.data.local.model.Todo;
import shvyn22.flexingnotes.databinding.FragmentAddEditBinding;
import shvyn22.flexingnotes.presentation.addedit.AddEditFragmentDirections.ActionAddEditFragmentToTodoDialog;
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
        adapter = new TodoAdapter(this::navigateToDialog);

        Long id = AddEditFragmentArgs.fromBundle(getArguments()).getId();
        viewModel.getNote(id).observe(getViewLifecycleOwner(), note -> {
            this.note = note;
            initUI();
            subscribeObservers();
        });
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
                viewModel.deleteTodo(
                    adapter.getCurrentList().get(viewHolder.getAdapterPosition()).id
                );
            }
        }).attachToRecyclerView(binding.rvTodos);

        binding.etTitle.setText(note.title);
        binding.etText.setText(note.text);

        binding.btnAddTodo.setOnClickListener(view -> navigateToDialog(new Todo()));

        requireActivity().getOnBackPressedDispatcher().addCallback(
            getViewLifecycleOwner(),
            new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    verifyNote();
                }
            }
        );

        setHasOptionsMenu(true);
    }

    private void navigateToDialog(Todo todo) {
        ActionAddEditFragmentToTodoDialog action = AddEditFragmentDirections
            .actionAddEditFragmentToTodoDialog();
        action.setId(todo.id);
        action.setText(todo.text);
        action.setIsCompleted(todo.isCompleted);

        Navigation
            .findNavController(binding.getRoot())
            .navigate(action);
    }

    private void subscribeObservers() {
        viewModel.getTodos(note.id).observe(getViewLifecycleOwner(), todos ->
            adapter.submitList(todos)
        );
    }

    @Override
    public void onResume() {
        super.onResume();

        getParentFragmentManager()
            .setFragmentResultListener(REQUEST_TODO, this, (requestKey, result) -> {
                long id = result.getLong(RESULT_TODO_ID);
                String text = result.getString(RESULT_TODO_TEXT);
                boolean isCompleted = result.getBoolean(RESULT_TODO_COMPLETENESS);
                Todo todo = new Todo(text, isCompleted, note.id);

                if (id == 0)
                    viewModel.insertTodo(todo);
                else {
                    todo.id = id;
                    viewModel.updateTodo(todo);
                }
            });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            verifyNote();
        return super.onOptionsItemSelected(item);
    }

    private void verifyNote() {
        if (binding.etTitle.getText() == null || binding.etText.getText() == null) return;

        String title = binding.etTitle.getText().toString();
        String text = binding.etText.getText().toString();
        if (!title.equals("") || !text.equals("")) {
            note.title = title;
            note.text = text;
            viewModel.updateNote(note);
            showSnackbar(R.string.text_note_saved);
        } else {
            viewModel.deleteNote(note.id);
            showSnackbar(R.string.text_note_not_saved);
        }

        Navigation.findNavController(binding.getRoot()).navigateUp();
    }

    private void showSnackbar(@StringRes int msg) {
        Snackbar
            .make(requireView(), msg, Snackbar.LENGTH_SHORT)
            .show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}