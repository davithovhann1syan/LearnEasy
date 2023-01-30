package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LessonActivity extends AppCompatActivity {

    TextView heading;
    TextView information;
    TextView backButton;
    String name;
    String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                name = null;
                info = null;

            } else {
                name = extras.getString("NAME");
                info = extras.getString("INFO");

            }
        } else {
            name = (String) savedInstanceState.getSerializable("NAME");
            info = (String) savedInstanceState.getSerializable("INFO");

        }

        setContentView(R.layout.activity_lesson);

        heading = findViewById(R.id.heading);
        information = findViewById(R.id.information);
        backButton = findViewById(R.id.backButton);

        heading.setText(name);

        information.setText(info);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();            }
        });

    }
}