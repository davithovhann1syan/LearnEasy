package com.example.learneasy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.checkerframework.common.value.qual.IntRangeFromNonNegative;

public class VocabularyLessonActivity extends AppCompatActivity {
    TextView information;
    TextView backButton, testButton, translateButton;
    String info;
    TextView heading;
    String name;
    String type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_lesson);
        heading = findViewById(R.id.heading);
        information = findViewById(R.id.information);
        backButton = findViewById(R.id.back);
        testButton = findViewById(R.id.test_button);
        translateButton = findViewById(R.id.translate_button);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                name = null;
                info = null;
                type = null;

            } else {
                name = extras.getString("HEADING");
                info = extras.getString("INFO");
                type = extras.getString("TYPE");
            }
        } else {
            name = (String) savedInstanceState.getSerializable("HEADING");
            info = (String) savedInstanceState.getSerializable("INFO");
            type = (String) savedInstanceState.getSerializable("TYPE");

        }



        heading.setText(name);
        information.setText(info);
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TranslateActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VocabularyTestActivity.class);
                intent.putExtra("TYPE", type);
                                startActivity(intent);
            }
        });
    }

    public String getType(){
        return type;
    }

}
