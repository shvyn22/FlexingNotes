package shvyn22.flexingnotes.presentation.addedit;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import shvyn22.flexingnotes.data.local.model.Todo;
import shvyn22.flexingnotes.databinding.ItemTodoBinding;

public class TodoAdapter extends ListAdapter<Todo, TodoAdapter.TodoViewHolder> {

    protected TodoAdapter() {
        super(TodoAdapter.DIFF_UTIL);
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
            )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {

        private final ItemTodoBinding binding;

        public TodoViewHolder(ItemTodoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Todo item) {
            binding.cbComplete.setChecked(item.isCompleted);
            binding.etTodo.setText(item.text);

            binding.cbComplete.setOnCheckedChangeListener((compoundButton, isChecked)
                -> item.isCompleted = isChecked
            );

            binding.etTodo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    item.text = charSequence.toString();
                }

                @Override
                public void afterTextChanged(Editable editable) { }
            });
        }
    }

    public static final DiffUtil.ItemCallback<Todo> DIFF_UTIL = new DiffUtil.ItemCallback<Todo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.text.equals(newItem.text);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.text.equals(newItem.text) && oldItem.isCompleted == newItem.isCompleted;
        }
    };
}
