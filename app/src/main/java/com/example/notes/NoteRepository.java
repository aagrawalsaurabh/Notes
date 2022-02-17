package com.example.notes;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;

    private LiveData<List<Note>> allNotes;

    NoteRepository(Application application) {
        NoteDatabase db = NoteDatabase.getDatabase(application);
        noteDao = db.getNoteDao();
        allNotes = noteDao.getAllNotes();
    }

    LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }


    void insert(Note note) {
    NoteDatabase.databaseWriteExecutor.execute(()->{
        noteDao.insert(note);
    });

    }
    void delete(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(()->{
            noteDao.delete(note);
        });
    }
}
