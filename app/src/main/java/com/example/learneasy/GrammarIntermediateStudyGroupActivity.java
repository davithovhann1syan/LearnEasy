package com.example.learneasy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GrammarIntermediateStudyGroupActivity extends AppCompatActivity {
    TextView sentenceStructure;
    TextView phrases;
    TextView clauses;
    TextView subjectVerbAgreement;
    TextView modifiers;
    TextView punctuation;
    TextView passiveVoice;
    TextView reportedSpeech;
    TextView conditionalSentences;
    TextView idiomaticExpressions;

    TextView back;
    String globalType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_intermediate_study_group);
        sentenceStructure = findViewById(R.id.sentence_structure);
        phrases = findViewById(R.id.phrases);
        clauses = findViewById(R.id.clauses);
        subjectVerbAgreement = findViewById(R.id.subject_verb_agreement);
        modifiers = findViewById(R.id.modifiers);
        passiveVoice = findViewById(R.id.passive_voice);
        punctuation = findViewById(R.id.punctuation);
        reportedSpeech = findViewById(R.id.reported_speech);
        conditionalSentences = findViewById(R.id.conditional_sentences);
        idiomaticExpressions = findViewById(R.id.idiomatic_expressions);
        back = findViewById(R.id.back);

        Bundle extras = getIntent().getExtras();
        globalType = extras.getString("GLOBALTYPE");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        sentenceStructure.setOnClickListener(v -> startActivity("sentenceStructure"));
        phrases.setOnClickListener(v -> startActivity("phrases"));
        clauses.setOnClickListener(v -> startActivity("clauses"));
        subjectVerbAgreement.setOnClickListener(v -> startActivity("subjectVerbAgreement"));
        modifiers.setOnClickListener(v -> startActivity("modifiers"));
        passiveVoice.setOnClickListener(v -> startActivity("passiveVoice"));
        punctuation.setOnClickListener(v -> startActivity("punctuation"));
        reportedSpeech.setOnClickListener(v -> startActivity("reportedSpeech"));
        conditionalSentences.setOnClickListener(v -> startActivity("conditionalSentences"));
        idiomaticExpressions.setOnClickListener(v -> startActivity("idiomaticExpressions"));

    }

    void startActivity(String type) {
        Intent intent = new Intent(getApplicationContext(), GrammarActivity.class);
        intent.putExtra("OPENTYPE", type);
        startActivity(intent);
    }
}