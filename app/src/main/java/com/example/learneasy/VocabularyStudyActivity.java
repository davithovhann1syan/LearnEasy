package com.example.learneasy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

        FirebaseFirestore firebaseFirestore;
        ArrayList<VocabularyLessonModel> arrayList = new ArrayList<>();

        back.setEnabled(false);
        colors.setEnabled(false);
        food.setEnabled(false);
        animals.setEnabled(false);
        clothing.setEnabled(false);
        transport.setEnabled(false);
        bodyParts.setEnabled(false);
        jobs.setEnabled(false);
        travel.setEnabled(false);
        sports.setEnabled(false);

        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                back.setEnabled(true);
                colors.setEnabled(true);
                food.setEnabled(true);
                animals.setEnabled(true);
                clothing.setEnabled(true);
                transport.setEnabled(true);
                bodyParts.setEnabled(true);
                jobs.setEnabled(true);
                travel.setEnabled(true);
                sports.setEnabled(true);
            }
        },1000);

        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("vocabularyLesson")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for (DocumentSnapshot documentSnapshot: task.getResult()){
                                        String type = documentSnapshot.get("type").toString();
                                        String heading = documentSnapshot.get("heading").toString();
                                        String info = documentSnapshot.get("info").toString();
                                        long id = (long) documentSnapshot.get("id");
                                        arrayList.add(new VocabularyLessonModel(type, heading, info, id));
                                        Collections.sort(arrayList, Comparator.comparingLong(VocabularyLessonModel::getId));
                                    }

                                    for (int i = 0; i < arrayList.size(); i++) {
                                        Log.i("DAS", arrayList.get(i).getType() + " X ");
                                    }
                                }
                            }
                        });

        back.setOnClickListener(v -> finish());
        colors.setOnClickListener(v -> startActivity(arrayList.get(0).getType(), arrayList.get(0).getHeading(), arrayList.get(0).getInfo()));
        food.setOnClickListener(v -> startActivity(arrayList.get(1).getType(), arrayList.get(1).getHeading(), arrayList.get(1).getInfo()));
        animals.setOnClickListener(v -> startActivity(arrayList.get(2).getType(), arrayList.get(2).getHeading(), arrayList.get(2).getInfo()));
        clothing.setOnClickListener(v -> startActivity(arrayList.get(3).getType(), arrayList.get(3).getHeading(), arrayList.get(3).getInfo()));
        transport.setOnClickListener(v -> startActivity(arrayList.get(4).getType(), arrayList.get(4).getHeading(), arrayList.get(4).getInfo()));
        bodyParts.setOnClickListener(v -> startActivity(arrayList.get(5).getType(), arrayList.get(5).getHeading(), arrayList.get(5).getInfo()));
        jobs.setOnClickListener(v -> startActivity(arrayList.get(6).getType(), arrayList.get(6).getHeading(), arrayList.get(6).getInfo()));
        travel.setOnClickListener(v -> startActivity(arrayList.get(7).getType(), arrayList.get(7).getHeading(), arrayList.get(7).getInfo()));
        sports.setOnClickListener(v -> startActivity(arrayList.get(8).getType(), arrayList.get(8).getHeading(), arrayList.get(8).getInfo()));

    }
    void startActivity(String type, String heading, String info){
        Intent intent = new Intent(getApplicationContext(), VocabularyLessonActivity.class);
        intent.putExtra("TYPE", type);
        intent.putExtra("HEADING", heading);
        intent.putExtra("INFO", info);
        startActivity(intent);
    }
}
