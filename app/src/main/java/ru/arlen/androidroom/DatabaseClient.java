package ru.arlen.androidroom;

import android.arch.persistence.room.Room;
import android.content.Context;
import ru.arlen.androidroom.dblayer.NotesDatabase;
import ru.arlen.androidroom.model.Props;

import static ru.arlen.androidroom.dblayer.NotesDatabase.MIGRATION_1_2;

public class DatabaseClient {
    private static DatabaseClient mInstance;
    private NotesDatabase mDatabase;

    private DatabaseClient(Context mCtx) {
        mDatabase = Room.databaseBuilder(mCtx, NotesDatabase.class, "notes_database")
                       .allowMainThreadQueries().allowMainThreadQueries()
                       .addMigrations(MIGRATION_1_2)
                        .fallbackToDestructiveMigration()
                       .build();
        mDatabase.getPropsDAO().insertProps(new Props("12", "BLACK"));
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public NotesDatabase getDatabase() {
        return mDatabase;
    }
}
