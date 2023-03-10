package com.example.learneasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GrammarStudyGroupActivity extends AppCompatActivity {
    TextView nouns;
    TextView pronouns;
    TextView verbs;
    TextView adverbs;
    TextView adjectives;
    TextView prepositions;
    TextView interjectionsConjunctions;
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_study_group);
        nouns = findViewById(R.id.nouns);
        pronouns = findViewById(R.id.pronouns);
        verbs = findViewById(R.id.verbs);
        adverbs = findViewById(R.id.adverbs);
        adjectives = findViewById(R.id.adjectives);
        prepositions = findViewById(R.id.prepositions);
        interjectionsConjunctions = findViewById(R.id.interjections_conjunction);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        nouns.setOnClickListener(v -> startActivity("nouns"));
        pronouns.setOnClickListener(v -> startActivity("pronouns"));
        verbs.setOnClickListener(v -> startActivity("verbs"));
        adverbs.setOnClickListener(v -> startActivity("adverbs"));
        adjectives.setOnClickListener(v -> startActivity("adjectives"));
        prepositions.setOnClickListener(v -> startActivity("prepositions"));
        interjectionsConjunctions.setOnClickListener(v -> startActivity("interjections"));

    }
    void startActivity(String type){
        Intent intent = new Intent(getApplicationContext(), GrammarActivity.class);
        intent.putExtra("OPENTYPE", type);
        startActivity(intent);
    }
}