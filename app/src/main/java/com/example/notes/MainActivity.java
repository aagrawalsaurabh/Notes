package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements INotesRVAdapter {
    RecyclerView recyclerView;
    private NoteViewModel viewModel;
    EditText inputText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = findViewById(R.id.input);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NotesRVAdapter adapter = new NotesRVAdapter(this,this);
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
//        viewModel.allNotes.observe(this, new Observer<List<Note>>() {
//            @Override
//            public void onChanged(List<Note> notes) {
//                if(!notes.isEmpty()) {
//                    adapter.updateList(notes);
//                }
//
//            }
//        });
        viewModel.getAllNotes().observe(this,notes -> {
            adapter.updateList(notes);
        });
    }

    @Override
    public void onItemClicked(Note note) {
        viewModel.deleteNote(note);
        Toast.makeText(this, note.text+" Deleted", Toast.LENGTH_SHORT).show();
    }

    public void submitData(View view) {
        String noteText = inputText.getText().toString();
        if(!noteText.isEmpty()) {
            viewModel.insertNote(new Note(noteText));
            Toast.makeText(this, noteText+" Inserted", Toast.LENGTH_SHORT).show();

        }
    }
}