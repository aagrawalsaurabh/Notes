package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesRVAdapter extends RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder> {
    ArrayList<Note> allNotes = new ArrayList<>();
    Context context;
    INotesRVAdapter listener;

    public NotesRVAdapter(Context context, INotesRVAdapter listener) {
        this.context = context;
        this.listener = listener;
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView deleteButton;

        public NoteViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text);
            deleteButton = (ImageView) view.findViewById(R.id.deleteButton);
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getImageView() {
            return deleteButton;
        }

    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NoteViewHolder viewHolder = new NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false));
        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(allNotes.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    void updateList(List<Note> newList) {
        allNotes.clear();
        allNotes.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(NotesRVAdapter.NoteViewHolder holder, int position) {
        Note currentNote = allNotes.get(position);
        holder.textView.setText(currentNote.text);
    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }
}

interface INotesRVAdapter{
    void onItemClicked(Note note);
}
