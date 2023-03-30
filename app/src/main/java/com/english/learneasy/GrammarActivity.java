package com.english.learneasy;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class GrammarActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    ConstraintLayout mainLayout;

    FirebaseFirestore firebaseFirestore;

    ArrayList<GrammarLessonsView> grammarLessonsViewArrayList;

    ProgressBar progressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);
        TextView back = findViewById(R.id.back);
        linearLayout = findViewById(R.id.lesson_linear_layout);
        progressBar = findViewById(R.id.progress_bar_grammar);
        mainLayout = findViewById(R.id.grammar_layout);

        PreferenceManager preferenceManager = new PreferenceManager(getApplicationContext());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Comparator<GrammarLessonsView> comparator = new Comparator<GrammarLessonsView>() {
            @Override
            public int compare(GrammarLessonsView viewLessonWidget, GrammarLessonsView t1) {
                return viewLessonWidget.name.compareTo(t1.name);
            }
        };

        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("grammarLessons")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Task<QuerySnapshot> snapshotTask = task;
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                String information = documentSnapshot.get("information").toString();
                                String title = documentSnapshot.get("title").toString();
                                String type = documentSnapshot.get("type").toString();
                                String subType = documentSnapshot.get("subType").toString();

                                firebaseFirestore.collection("userScores")
                                        .whereEqualTo("TYPE", type)
                                        .whereEqualTo("SUBTYPE", subType)
                                        .whereEqualTo("USERID", FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                                if (task.isSuccessful()) {

                                                    String score;
                                                    if (task.getResult().getDocuments().isEmpty()) {
                                                        score = "0";
                                                    } else {
                                                        score = task.getResult().getDocuments().get(0).get("SCORE") + "";
                                                    }

                                                    grammarLessonsViewArrayList.add(new GrammarLessonsView(getApplicationContext(), title, information, type, subType, score));

                                                    if (grammarLessonsViewArrayList.size() == snapshotTask.getResult().getDocuments().size()) {
                                                        grammarLessonsViewArrayList.sort(comparator);
                                                        drawWidgets(grammarLessonsViewArrayList);
                                                    }

                                                } else {
                                                    Toast.makeText(GrammarActivity.this, "" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        });


                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

        grammarLessonsViewArrayList = new ArrayList<>();

    }

    public void drawWidgets(ArrayList<GrammarLessonsView> list) {

        Intent intent = getIntent();
        String type = intent.getStringExtra("OPENTYPE");

        int i = 0;

        for (GrammarLessonsView widget : list) {
            if (Objects.equals(widget.getType(), type)) {
                linearLayout.addView(widget);
                i++;
            }
        }
        if (i == 0) {
            alertDialogEmpty();
        }
    }

    void alertDialogEmpty() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GrammarActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_not_ready, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        dialog.show();
        dialogView.findViewById(R.id.dialog_finish).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                mainLayout.setClickable(false);
                finish();
            }
        });
    }

}