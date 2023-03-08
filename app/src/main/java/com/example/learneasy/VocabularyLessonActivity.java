package com.example.learneasy;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VocabularyLessonActivity extends AppCompatActivity {

    TextView information;
    TextView backButton, testButton;
    String info;
    TextView heading;

    String name;
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
                name = extras.getString("HEADING");
                info = extras.getString("INFO");
                type = extras.getString("TYPE");

            }
        } else {
            name = (String) savedInstanceState.getSerializable("HEADING");
            info = (String) savedInstanceState.getSerializable("INFO");
            type = (String) savedInstanceState.getSerializable("TYPE");

        }



        setContentView(R.layout.activity_vocabulary_lesson);
        heading = findViewById(R.id.heading);
        information = findViewById(R.id.information);
        backButton = findViewById(R.id.back_button);
        testButton = findViewById(R.id.test_button);

        heading.setText(name);
        information.setText(info);

        /*testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GrammarTestActivity.class);
                intent.putExtra("SUBTYPE", subType);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });*/

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public String getType(){
        return type;
    }

}