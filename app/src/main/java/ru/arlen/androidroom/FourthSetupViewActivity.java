package ru.arlen.androidroom;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import ru.arlen.androidroom.dao.PropsDAO;
import ru.arlen.androidroom.dblayer.NotesDatabase;
import ru.arlen.androidroom.model.Props;

import java.util.Arrays;

public class FourthSetupViewActivity extends Activity {
    private static final String[] COLORS = {"BLACK", "BLUE", "YELLOW", "RED"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_view);

        NotesDatabase db = DatabaseClient.getInstance(getApplicationContext()).getDatabase();
        final PropsDAO propsDAO = db.getPropsDAO();
        final Props props = propsDAO.getProps();

        final EditText title = findViewById(R.id.fTextSize);
        title.setText(props.getSize());

        final Spinner dropdown = findViewById(R.id.fSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, COLORS);
        dropdown.setAdapter(adapter);
        dropdown.setSelection(Arrays.asList(COLORS).indexOf(props.getColor()));

        final EditText size = findViewById(R.id.fTextSize);
        View save = findViewById(R.id.fSaveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!size.getText().toString().isEmpty()){
                    props.setSize(size.getText().toString());
                    props.setColor(dropdown.getSelectedItem().toString());
                    propsDAO.updateProps(props);
                    startActivity(new Intent(FourthSetupViewActivity.this, FirstMainActivity.class));
                }
            }
        });

        View cancel = findViewById(R.id.fCancelBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FourthSetupViewActivity.this, FirstMainActivity.class));
            }
        });
    }
}
