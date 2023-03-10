package com.example.learneasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudyOptionsActivity extends AppCompatActivity {

    TextView grammar;
    TextView back;
    TextView vocabularyTest;
    TextView translate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_options);
        grammar = findViewById(R.id.grammar);
        back = findViewById(R.id.back);
        vocabularyTest = findViewById(R.id.vocabulary_test);
        translate = findViewById(R.id.translate);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GrammarStudyGroupActivity.class);
                startActivity(intent);
            }
        });

        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TranslateActivity.class);
                startActivity(intent);
            }
        });

        vocabularyTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VocabularyStudyActivity.class);
                startActivity(intent);
            }
        });

    }
}