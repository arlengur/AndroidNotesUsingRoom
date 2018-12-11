package ru.arlen.androidroom.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import ru.arlen.androidroom.model.Note;

import java.util.List;

@Dao
public interface NoteDAO {
    @Query("select title from note")
    public List<String> getNotes();

    @Query("select * from note where title = :title")
    public Note getNote(String title);

    @Update
    public void updateNote(Note note);

    @Insert
    public void addNote(Note note);
}
