package com.english.learneasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GrammarAdvancedStudyGroupActivity extends AppCompatActivity {

    TextView complexSentences;
    TextView complexVerbTenses;
    TextView complexNounUsage;
    TextView complexAdjectiveAdverbUsage;
    TextView complexPunctuationMechanics;
    TextView complexStyleUsage;
    TextView complexSyntax;
    TextView back;

    String globalType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_advanced_study_group);
        complexSentences = findViewById(R.id.complex_sentences);
        complexVerbTenses = findViewById(R.id.complex_verb_tenses);
        complexNounUsage = findViewById(R.id.complex_noun_pronoun_usage);
        complexAdjectiveAdverbUsage = findViewById(R.id.complex_adjective_adverb_usage);
        complexPunctuationMechanics = findViewById(R.id.complex_punctuation_mechanics);
        complexStyleUsage = findViewById(R.id.complex_style_usage);
        complexSyntax = findViewById(R.id.complex_syntax);

        back = findViewById(R.id.back);


        Bundle extras = getIntent().getExtras();
        globalType = extras.getString("GLOBALTYPE");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        complexSentences.setOnClickListener(v -> startActivity("complexSentences"));
        complexVerbTenses.setOnClickListener(v -> startActivity("complexVerbTenses"));
        complexNounUsage.setOnClickListener(v -> startActivity("complexNounUsage"));
        complexAdjectiveAdverbUsage.setOnClickListener(v -> startActivity("complexAdjectiveAdverbUsage"));
        complexPunctuationMechanics.setOnClickListener(v -> startActivity("complexPunctuationMechanics"));
        complexStyleUsage.setOnClickListener(v -> startActivity("complexStyleUsage"));
        complexSyntax.setOnClickListener(v -> startActivity("complexSyntax"));

    }

    void startActivity(String type) {
        Intent intent = new Intent(getApplicationContext(), GrammarActivity.class);
        intent.putExtra("OPENTYPE", type);
        startActivity(intent);
    }
}