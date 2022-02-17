package com.example.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    LiveData<List<Note>> allNotes;
    NoteRepository repository;

    public NoteViewModel(Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    void insertNote(Note note){
            repository.insert(note);
    }

    void deleteNote(Note note){
            repository.delete(note);
    }

    LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
