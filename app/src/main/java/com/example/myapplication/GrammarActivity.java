package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;

import javax.crypto.spec.PBEKeySpec;

import developers.mobile.abt.FirebaseAbt;

public class GrammarActivity extends AppCompatActivity {

    ViewLessonWidget viewLessonWidget;
    LinearLayout linearLayout;

    String highScore = "0";
    String nouns;
    String verbs;
    String adjectives;
    String prepositions;
    String interjections;

    FirebaseFirestore firebaseFirestore;

    ArrayList<ViewLessonWidget> viewLessonWidgetArrayList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);
        TextView back = findViewById(R.id.back);
        linearLayout = findViewById(R.id.lesson_linear_layout);


       PreferenceManager preferenceManager = new PreferenceManager(getApplicationContext());


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Comparator<ViewLessonWidget> comparator = new Comparator<ViewLessonWidget>() {
            @Override
            public int compare(ViewLessonWidget viewLessonWidget, ViewLessonWidget t1) {
                return viewLessonWidget.name.compareTo(t1.name);
            }
        };

        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("grammarLessons")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot: task.getResult()){
                                String information = documentSnapshot.get("information").toString();
                                String title = documentSnapshot.get("title").toString();
                                String type = documentSnapshot.get("type").toString();
                                String subType = documentSnapshot.get("subType").toString();
                                String score = preferenceManager.getString(subType);



                                if (score == null){
                                    score = "0";
                                }
                               viewLessonWidgetArrayList.add(new ViewLessonWidget(getApplicationContext(), title, information , type, subType, score));
                            }

                            Collections.sort(viewLessonWidgetArrayList, comparator);
                            drawWidgets(viewLessonWidgetArrayList);

                        }
                    }
                });

        viewLessonWidgetArrayList = new ArrayList<>();



    }

    public void drawWidgets(ArrayList<ViewLessonWidget> list) {

        Bundle extras = getIntent().getExtras();
        Intent intent = getIntent();
        String type = intent.getStringExtra("OPENTYPE");

        for (ViewLessonWidget widget : list) {
            if (Objects.equals(widget.getType(), type)){
                linearLayout.addView(widget);
            }
        }
    }

}