package ru.arlen.androidroom;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import ru.arlen.androidroom.dao.NoteDAO;
import ru.arlen.androidroom.dblayer.NotesDatabase;
import ru.arlen.androidroom.model.Note;

public class SecondCreateNoteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        NotesDatabase db = DatabaseClient.getInstance(getApplicationContext()).getDatabase();
        final NoteDAO noteDAO = db.getNoteDAO();

        final EditText title = findViewById(R.id.sTitle);
        final EditText content = findViewById(R.id.sContent);
        View create = findViewById(R.id.sCreateBtn);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!title.getText().toString().isEmpty() && !content.getText().toString().isEmpty()){
                    Note note = new Note(title.getText().toString(), content.getText().toString());
                    noteDAO.addNote(note);
                    startActivity(new Intent(SecondCreateNoteActivity.this, FirstMainActivity.class));
                }
            }
        });

        View cancel = findViewById(R.id.sCancelBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondCreateNoteActivity.this, FirstMainActivity.class));
            }
        });
    }
}
