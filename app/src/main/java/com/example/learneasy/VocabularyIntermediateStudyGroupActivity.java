package com.example.learneasy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class VocabularyIntermediateStudyGroupActivity extends AppCompatActivity {
    TextView back;
    AppCompatButton technology, business, healthAndMedicine, science, lawAndPolitics, education, artsAndCulture, environment, psychology;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_intermediate_study_groups);

        back = findViewById(R.id.back);
        technology = findViewById(R.id.technology);
        business = findViewById(R.id.business);
        healthAndMedicine = findViewById(R.id.health_and_medicine);
        science = findViewById(R.id.science);
        lawAndPolitics = findViewById(R.id.law_and_politics);
        education = findViewById(R.id.education);
        artsAndCulture = findViewById(R.id.arts_and_culture);
        environment = findViewById(R.id.environment);
        psychology = findViewById(R.id.psychology);
        progressBar = findViewById(R.id.progress_bar_vocabulary);

        FirebaseFirestore firebaseFirestore;
        ArrayList<VocabularyLessonModel> arrayList = new ArrayList<>();

        technology.setEnabled(false);
        business.setEnabled(false);
        healthAndMedicine.setEnabled(false);
        science.setEnabled(false);
        lawAndPolitics.setEnabled(false);
        education.setEnabled(false);
        artsAndCulture.setEnabled(false);
        environment.setEnabled(false);
        psychology.setEnabled(false);

        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("vocabularyLesson")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                String type = documentSnapshot.get("type").toString();
                                String heading = documentSnapshot.get("heading").toString();
                                String info = documentSnapshot.get("info").toString();
                                long id = (long) documentSnapshot.get("id");
                                arrayList.add(new VocabularyLessonModel(type, heading, info, id));
                                Collections.sort(arrayList, Comparator.comparingLong(VocabularyLessonModel::getId));

                                if (arrayList.size() == task.getResult().getDocuments().size()) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    technology.setEnabled(true);
                                    business.setEnabled(true);
                                    healthAndMedicine.setEnabled(true);
                                    science.setEnabled(true);
                                    lawAndPolitics.setEnabled(true);
                                    education.setEnabled(true);
                                    artsAndCulture.setEnabled(true);
                                    environment.setEnabled(true);
                                    psychology.setEnabled(true);
                                }

                            }

                        }
                    }
                });

        back.setOnClickListener(v -> finish());
        technology.setOnClickListener(v -> startActivity(arrayList.get(9).getType(), arrayList.get(9).getHeading(), arrayList.get(9).getInfo()));
        business.setOnClickListener(v -> startActivity(arrayList.get(10).getType(), arrayList.get(10).getHeading(), arrayList.get(10).getInfo()));
        healthAndMedicine.setOnClickListener(v -> startActivity(arrayList.get(11).getType(), arrayList.get(11).getHeading(), arrayList.get(11).getInfo()));
        science.setOnClickListener(v -> startActivity(arrayList.get(12).getType(), arrayList.get(12).getHeading(), arrayList.get(12).getInfo()));
        lawAndPolitics.setOnClickListener(v -> startActivity(arrayList.get(13).getType(), arrayList.get(13).getHeading(), arrayList.get(13).getInfo()));
        education.setOnClickListener(v -> startActivity(arrayList.get(14).getType(), arrayList.get(14).getHeading(), arrayList.get(14).getInfo()));
        artsAndCulture.setOnClickListener(v -> startActivity(arrayList.get(15).getType(), arrayList.get(15).getHeading(), arrayList.get(15).getInfo()));
        environment.setOnClickListener(v -> startActivity(arrayList.get(16).getType(), arrayList.get(16).getHeading(), arrayList.get(16).getInfo()));
        psychology.setOnClickListener(v -> startActivity(arrayList.get(17).getType(), arrayList.get(17).getHeading(), arrayList.get(17).getInfo()));

    }

    void startActivity(String type, String heading, String info) {
        Intent intent = new Intent(getApplicationContext(), VocabularyLessonActivity.class);
        intent.putExtra("TYPE", type);
        intent.putExtra("HEADING", heading);
        intent.putExtra("INFO", info);
        startActivity(intent);
    }

}
