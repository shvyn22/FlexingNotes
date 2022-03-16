package shvyn22.flexingnotes.presentation.addedit;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import shvyn22.flexingnotes.data.local.model.Todo;
import shvyn22.flexingnotes.databinding.ItemTodoBinding;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private final ArrayList<Todo> todos = new ArrayList<>();

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
        holder.bind(todos.get(position));
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateAndNotify(ArrayList<Todo> todos) {
        this.todos.clear();
        this.todos.addAll(todos);
        notifyDataSetChanged();
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
                public void afterTextChanged(Editable editable) {
                }
            });
        }
    }
}
