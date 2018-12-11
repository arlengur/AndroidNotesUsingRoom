package ru.arlen.androidroom;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import ru.arlen.androidroom.dao.NoteDAO;
import ru.arlen.androidroom.dao.PropsDAO;
import ru.arlen.androidroom.dblayer.NotesDatabase;
import ru.arlen.androidroom.model.Note;
import ru.arlen.androidroom.model.Props;

import static ru.arlen.androidroom.FirstMainActivity.TITLE;

public class ThirdEditNoteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        NotesDatabase db = DatabaseClient.getInstance(getApplicationContext()).getDatabase();
        final NoteDAO noteDAO = db.getNoteDAO();
        final PropsDAO propsDAO = db.getPropsDAO();
        Props props = propsDAO.getProps();

        String titleParam = getIntent().getStringExtra(TITLE);
        final Note note = noteDAO.getNote(titleParam);
        final EditText title = findViewById(R.id.tTitle);
        title.setText(note.getTitle());
        final EditText content = findViewById(R.id.tContent);
        content.setText(note.getContent());

        // Text style
        content.setTextSize(Float.parseFloat(props.getSize()));
        content.setTextColor(Color.parseColor(props.getColor()));

        View update = findViewById(R.id.tUpdateBtn);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!title.getText().toString().isEmpty() && !content.getText().toString().isEmpty()){
                    note.setTitle(title.getText().toString());
                    note.setContent(content.getText().toString());
                    noteDAO.updateNote(note);
                    startActivity(new Intent(ThirdEditNoteActivity.this, FirstMainActivity.class));
                }
            }
        });

        View cancel = findViewById(R.id.tCancelBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdEditNoteActivity.this, FirstMainActivity.class));
            }
        });
    }
}
