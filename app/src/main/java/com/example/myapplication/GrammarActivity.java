package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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

    LinearLayout linearLayout;

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



                                firebaseFirestore.collection("userScores")
                                        .whereEqualTo("TYPE",type)
                                        .whereEqualTo("SUBTYPE", subType)
                                        .whereEqualTo("USERID", FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                                if (task.isSuccessful()){

                                                    String score;
                                                    if (task.getResult().getDocuments().isEmpty()){
                                                        score = "0";

                                                    } else {
                                                        score = task.getResult().getDocuments().get(0).get("SCORE")+"";
                                                    }
                                                    viewLessonWidgetArrayList.add(new ViewLessonWidget(getApplicationContext(), title, information , type, subType, score));
                                                }
                                                else {
                                                    Toast.makeText(GrammarActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        });





                            }
                            (new Handler()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    viewLessonWidgetArrayList.sort(comparator);
                                    drawWidgets(viewLessonWidgetArrayList);
                                }
                            }, 500);


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