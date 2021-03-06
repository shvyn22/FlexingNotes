package shvyn22.flexingnotes.presentation.notes;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import shvyn22.flexingnotes.data.local.model.Note;
import shvyn22.flexingnotes.databinding.ItemNoteBinding;

public class NotesAdapter extends ListAdapter<Note, NotesAdapter.NoteViewHolder> {

    private final OnItemClickListener listener;

    protected NotesAdapter(OnItemClickListener listener) {
        super(NotesAdapter.DIFF_UTIL);
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
            )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private final ItemNoteBinding binding;

        public NoteViewHolder(ItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Note item) {
            binding.getRoot().setOnClickListener(v -> listener.onItemClick(item.id));

            binding.tvTitle.setText(item.title);
            binding.tvText.setText(item.text);
        }
    }

    public static final DiffUtil.ItemCallback<Note> DIFF_UTIL = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.id == newItem.id
                && oldItem.title.equals(newItem.title)
                && oldItem.text.equals(newItem.text);
        }
    };

    interface OnItemClickListener {
        void onItemClick(long id);
    }
}