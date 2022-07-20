package shvyn22.flexingnotes.presentation.addedit;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import shvyn22.flexingnotes.R;
import shvyn22.flexingnotes.data.local.model.Todo;
import shvyn22.flexingnotes.databinding.ItemTodoBinding;

public class TodoAdapter extends ListAdapter<Todo, TodoAdapter.TodoViewHolder> {

    private final OnItemClickListener listener;

    public TodoAdapter(OnItemClickListener listener) {
        super(TodoAdapter.DIFF_UTIL);
        this.listener = listener;
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

    class TodoViewHolder extends RecyclerView.ViewHolder {

        private final ItemTodoBinding binding;

        public TodoViewHolder(ItemTodoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Todo item) {
            binding.getRoot().setOnClickListener(v -> listener.onItemClick(item));
            binding.tvTodo.setText(item.text);

            if (item.isCompleted) {
                binding.getRoot().setCardBackgroundColor(
                    itemView.getResources()
                        .getColor(R.color.magenta_400, itemView.getContext().getTheme())
                );
                binding.tvTodo.setPaintFlags(
                    binding.tvTodo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
                );
            } else {
                binding.getRoot().setCardBackgroundColor(
                    itemView.getResources()
                        .getColor(R.color.magenta_600, itemView.getContext().getTheme())
                );
                binding.tvTodo.setPaintFlags(
                    binding.tvTodo.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG)
                );
            }
        }
    }

    public static final DiffUtil.ItemCallback<Todo> DIFF_UTIL = new DiffUtil.ItemCallback<Todo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.id == newItem.id
                && oldItem.text.equals(newItem.text)
                && oldItem.isCompleted == newItem.isCompleted
                && oldItem.noteId == newItem.noteId;
        }
    };

    interface OnItemClickListener {
        void onItemClick(Todo todo);
    }
}