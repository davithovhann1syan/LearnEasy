package com.example.learneasy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class VocabularyStudyActivity extends AppCompatActivity {

    TextView back;

    AppCompatButton colors, animals, food, clothing , transport, bodyParts, jobs, sports, travel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_study);

        back = findViewById(R.id.back);
        colors = findViewById(R.id.colors);
        food = findViewById(R.id.food);
        animals = findViewById(R.id.animals);
        clothing = findViewById(R.id.clothing);
        transport = findViewById(R.id.transport);
        bodyParts = findViewById(R.id.body_parts);
        jobs = findViewById(R.id.jobs);
        travel = findViewById(R.id.travel);
        sports = findViewById(R.id.sports);


        back.setOnClickListener(v -> finish());
        colors.setOnClickListener(v -> startActivity("colors", "Colors", "red, blue"));
        food.setOnClickListener(v -> startActivity("food", "Food", "pizza, burger"));
        animals.setOnClickListener(v -> startActivity("animals", "Animals", "dog, cat"));
        clothing.setOnClickListener(v -> startActivity("clothing", "Clothing", "hat, coat"));
        transport.setOnClickListener(v -> startActivity("transport", "Transport", "car, bike"));
        bodyParts.setOnClickListener(v -> startActivity("bodyParts", "Body parts", "head, leg"));
        jobs.setOnClickListener(v -> startActivity("jobs", "Jobs", "doctor, driver"));
        travel.setOnClickListener(v -> startActivity("travel", "Travel", "airplane, bag"));
        sports.setOnClickListener(v -> startActivity("sports", "Sports", "football, basketball"));



    }
    void startActivity(String type, String heading, String info){
        Intent intent = new Intent(getApplicationContext(), VocabularyLessonActivity.class);
        intent.putExtra("TYPE", type);
        intent.putExtra("HEADING", heading);
        intent.putExtra("INFO", info);
        startActivity(intent);
    }
}
