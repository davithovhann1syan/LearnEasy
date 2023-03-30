package com.english.learneasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class StudyOptionsActivity extends AppCompatActivity {

    TextView grammar;
    TextView back;
    TextView vocabularyTest;
    TextView translate;
    String globalType;

    TextView heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_options);
        grammar = findViewById(R.id.grammar);
        back = findViewById(R.id.back);
        vocabularyTest = findViewById(R.id.vocabulary_test);
        translate = findViewById(R.id.translate);

        Bundle extras = getIntent().getExtras();
        globalType = extras.getString("GLOBALTYPE");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(globalType, "beginner")) {
                    Intent intent = new Intent(getApplicationContext(), GrammarBeginnerStudyGroupActivity.class);
                    intent.putExtra("GLOBALTYPE", globalType);
                    startActivity(intent);

                } else if (Objects.equals(globalType, "intermediate")) {
                    Intent intent = new Intent(getApplicationContext(), GrammarIntermediateStudyGroupActivity.class);
                    intent.putExtra("GLOBALTYPE", globalType);
                    startActivity(intent);

                } else if (Objects.equals(globalType, "advanced")) {
                    Intent intent = new Intent(getApplicationContext(), GrammarAdvancedStudyGroupActivity.class);
                    intent.putExtra("GLOBALTYPE", globalType);
                    startActivity(intent);

                }
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
                if (Objects.equals(globalType, "beginner")) {
                    Intent intent = new Intent(getApplicationContext(), VocabularyBeginnerStudyGroupActivity.class);
                    intent.putExtra("GLOBALTYPE", globalType);
                    startActivity(intent);

                } else if (Objects.equals(globalType, "intermediate")) {
                    Intent intent = new Intent(getApplicationContext(), VocabularyIntermediateStudyGroupActivity.class);
                    intent.putExtra("GLOBALTYPE", globalType);
                    startActivity(intent);

                } else if (Objects.equals(globalType, "advanced")) {
                    Intent intent = new Intent(getApplicationContext(), VocabularyAdvancedStudyGroupActivity.class);
                    intent.putExtra("GLOBALTYPE", globalType);
                    startActivity(intent);

                }
            }
        });

    }
}