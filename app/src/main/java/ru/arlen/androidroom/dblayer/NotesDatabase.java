package ru.arlen.androidroom.dblayer;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;
import ru.arlen.androidroom.dao.NoteDAO;
import ru.arlen.androidroom.dao.PropsDAO;
import ru.arlen.androidroom.model.Note;
import ru.arlen.androidroom.model.Props;

@Database(entities = {Note.class, Props.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NoteDAO getNoteDAO();
    public abstract PropsDAO getPropsDAO();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase db) {
            db.execSQL("drop table if exists note");
            db.execSQL("drop table if exists props");
            db.execSQL("create table note(id integer primary key not null, title text, content text)");
            db.execSQL("insert into note(title, content) values ('first', 'first text')");
            db.execSQL("insert into note(title, content) values ('second', 'second text')");
            db.execSQL("create table props(id integer primary key not null, size text, color text)");
            db.execSQL("insert into props(size, color) values ('12', 'BLACK')");
        }
    };
}