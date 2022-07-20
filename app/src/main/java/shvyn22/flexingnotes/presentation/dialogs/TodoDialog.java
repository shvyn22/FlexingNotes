package shvyn22.flexingnotes.presentation.dialogs;

import static android.view.ViewGroup.LayoutParams;
import static shvyn22.flexingnotes.util.Constants.REQUEST_TODO;
import static shvyn22.flexingnotes.util.Constants.RESULT_TODO_COMPLETENESS;
import static shvyn22.flexingnotes.util.Constants.RESULT_TODO_ID;
import static shvyn22.flexingnotes.util.Constants.RESULT_TODO_TEXT;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import shvyn22.flexingnotes.R;
import shvyn22.flexingnotes.databinding.DialogTodoBinding;

public class TodoDialog extends DialogFragment {

    public TodoDialog() {
        super(R.layout.dialog_todo);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getDialog() != null) {
            getDialog().getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DialogTodoBinding binding = DialogTodoBinding.bind(view);
        TodoDialogArgs args = TodoDialogArgs.fromBundle(getArguments());

        binding.etTodo.setText(args.getText());
        binding.cbComplete.setChecked(args.getIsCompleted());

        binding.btnSave.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putLong(RESULT_TODO_ID, args.getId());
            bundle.putString(RESULT_TODO_TEXT, binding.etTodo.getText().toString());
            bundle.putBoolean(RESULT_TODO_COMPLETENESS, binding.cbComplete.isChecked());

            getParentFragmentManager().setFragmentResult(REQUEST_TODO, bundle);

            dismiss();
        });
    }
}