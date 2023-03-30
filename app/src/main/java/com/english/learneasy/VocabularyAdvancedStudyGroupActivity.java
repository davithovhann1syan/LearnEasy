package com.english.learneasy;

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

public class VocabularyAdvancedStudyGroupActivity extends AppCompatActivity {
    TextView back;

    AppCompatButton economicsAndFinance;
    AppCompatButton internationalRelation;
    AppCompatButton it;
    AppCompatButton marketingAndAdvertising;
    AppCompatButton journalismAndMedia;
    AppCompatButton linguistics;
    AppCompatButton philosophy;
    AppCompatButton history;
    AppCompatButton geography;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_advanced_study_group);

        back = findViewById(R.id.back);
        economicsAndFinance = findViewById(R.id.economics_and_finance);
        internationalRelation = findViewById(R.id.international_relations);
        it = findViewById(R.id.it);
        marketingAndAdvertising = findViewById(R.id.marketing_and_advertising);
        journalismAndMedia = findViewById(R.id.journalism_and_media);
        linguistics = findViewById(R.id.linguistics);
        philosophy = findViewById(R.id.philosophy);
        history = findViewById(R.id.history);
        geography = findViewById(R.id.geography);
        progressBar = findViewById(R.id.progress_bar_vocabulary);

        FirebaseFirestore firebaseFirestore;
        ArrayList<VocabularyLessonModel> arrayList = new ArrayList<>();

        economicsAndFinance.setEnabled(false);
        internationalRelation.setEnabled(false);
        it.setEnabled(false);
        marketingAndAdvertising.setEnabled(false);
        journalismAndMedia.setEnabled(false);
        linguistics.setEnabled(false);
        philosophy.setEnabled(false);
        history.setEnabled(false);
        geography.setEnabled(false);


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
                                    economicsAndFinance.setEnabled(true);
                                    internationalRelation.setEnabled(true);
                                    it.setEnabled(true);
                                    marketingAndAdvertising.setEnabled(true);
                                    journalismAndMedia.setEnabled(true);
                                    linguistics.setEnabled(true);
                                    philosophy.setEnabled(true);
                                    history.setEnabled(true);
                                    geography.setEnabled(true);
                                }

                            }

                        }
                    }
                });

        back.setOnClickListener(v -> finish());
        economicsAndFinance.setOnClickListener(v -> startActivity(arrayList.get(18).getType(), arrayList.get(18).getHeading(), arrayList.get(18).getInfo()));
        internationalRelation.setOnClickListener(v -> startActivity(arrayList.get(19).getType(), arrayList.get(19).getHeading(), arrayList.get(19).getInfo()));
        it.setOnClickListener(v -> startActivity(arrayList.get(20).getType(), arrayList.get(20).getHeading(), arrayList.get(20).getInfo()));
        marketingAndAdvertising.setOnClickListener(v -> startActivity(arrayList.get(21).getType(), arrayList.get(21).getHeading(), arrayList.get(21).getInfo()));
        journalismAndMedia.setOnClickListener(v -> startActivity(arrayList.get(22).getType(), arrayList.get(22).getHeading(), arrayList.get(22).getInfo()));
        linguistics.setOnClickListener(v -> startActivity(arrayList.get(23).getType(), arrayList.get(23).getHeading(), arrayList.get(23).getInfo()));
        philosophy.setOnClickListener(v -> startActivity(arrayList.get(24).getType(), arrayList.get(24).getHeading(), arrayList.get(24).getInfo()));
        history.setOnClickListener(v -> startActivity(arrayList.get(25).getType(), arrayList.get(25).getHeading(), arrayList.get(25).getInfo()));
        geography.setOnClickListener(v -> startActivity(arrayList.get(26).getType(), arrayList.get(26).getHeading(), arrayList.get(26).getInfo()));

    }

    void startActivity(String type, String heading, String info) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), VocabularyLessonActivity.class);
                intent.putExtra("TYPE", type);
                intent.putExtra("HEADING", heading);
                intent.putExtra("INFO", info);
                startActivity(intent);
            }
        }, 0);

    }
}
