package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GrammarStudyGroupActivity extends AppCompatActivity {

    TextView nounsPronouns;
    TextView verbsAdverbs;
    TextView adjectives;
    TextView prepositions;
    TextView interjectionsConjunctions;

    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_study_group);

        nounsPronouns = findViewById(R.id.nouns_and_pronouns);
        verbsAdverbs = findViewById(R.id.verbs_and_adverbs);
        adjectives = findViewById(R.id.adjectives);
        prepositions = findViewById(R.id.prepositions);
        interjectionsConjunctions = findViewById(R.id.interjections_conjuctions);
        back = findViewById(R.id.back);

        back.setOnClickListener(v -> finish());

        nounsPronouns.setOnClickListener(v -> startActivity());
        verbsAdverbs.setOnClickListener(v -> startActivity());
        adjectives.setOnClickListener(v -> startActivity());
        prepositions.setOnClickListener(v -> startActivity());
        interjectionsConjunctions.setOnClickListener(v -> startActivity());




    }
    void startActivity(){
        Intent intent = new Intent(getApplicationContext(), LessonActivity.class);
        startActivity(intent);
    }
}