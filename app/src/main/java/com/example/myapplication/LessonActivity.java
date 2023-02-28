package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LessonActivity extends AppCompatActivity {

    TextView heading;
    TextView information;
    TextView backButton, testButton;
    String name;
    String info;

    String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                name = null;
                info = null;
                type = null;

            } else {
                name = extras.getString("NAME");
                info = extras.getString("INFO");
                type = extras.getString("TYPE");

            }
        } else {
            name = (String) savedInstanceState.getSerializable("NAME");
            info = (String) savedInstanceState.getSerializable("INFO");
            type = (String) savedInstanceState.getSerializable("TYPE");

        }



        setContentView(R.layout.activity_grammar_lesson);

        heading = findViewById(R.id.heading);
        information = findViewById(R.id.information);
        backButton = findViewById(R.id.backButton);
        testButton = findViewById(R.id.testButton);

        heading.setText(name);

        information.setText(info);

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GrammarTestActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();            }
        });

    }

    public String getType(){
        return type;
    }
}