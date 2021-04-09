package com.example.pms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddActivity extends AppCompatActivity {

    EditText name_input, number_input;
    RadioGroup myradiogroup;
    RadioButton type_input;
    Button add_button;
    String gname, gphone, gtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        number_input = findViewById(R.id.number_input);
        myradiogroup = findViewById(R.id.myradiogroup);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                gname = name_input.getText().toString().trim();
                gphone = number_input.getText().toString().trim();
                int radiobuttonid = myradiogroup.getCheckedRadioButtonId();
                type_input = findViewById(radiobuttonid);
                gtype = type_input.getText().toString().trim();
                myDB.addGuest(gname, gphone, gtype);
                Intent i = new Intent(AddActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });
    }
}