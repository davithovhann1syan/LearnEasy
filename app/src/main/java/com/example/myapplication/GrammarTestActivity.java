package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GrammarTestActivity extends AppCompatActivity implements View.OnClickListener{

        int bestScore = 0;

        String subType, type;

        FirebaseFirestore firebaseFirestore;

        TextView totalQuestionsTextView, currentQuestionView, goBack;
        TextView questionTextView;
        AppCompatButton ansA, ansB, ansC, ansD;
        AppCompatButton submitBtn, nextBtn;




        int score = 0;
        int totalQuestion = 1;
        int currentQuestionIndex = 0;
        AppCompatButton rightAnswer, wrongAnswer, selectedAnswer;

        ArrayList<GrammarQuizModel> arrayList = new ArrayList<>();

        @SuppressLint("SetTextI18n")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_grammar_test_activity);

            goBack = findViewById(R.id.back);
            totalQuestionsTextView = findViewById(R.id.total_questions);
            questionTextView = findViewById(R.id.question);
            nextBtn = findViewById(R.id.next_btn);
            ansA = findViewById(R.id.ans_A);
            ansB = findViewById(R.id.ans_B);
            ansC = findViewById(R.id.ans_C);
            ansD = findViewById(R.id.ans_D);
            submitBtn = findViewById(R.id.submit_btn);
            currentQuestionView = findViewById(R.id.current_question);

            ansA.setOnClickListener(this);
            ansB.setOnClickListener(this);
            ansC.setOnClickListener(this);
            ansD.setOnClickListener(this);
            submitBtn.setOnClickListener(this);
            nextBtn.setOnClickListener(this);
            totalQuestionsTextView.setText(totalQuestion+"");


            goBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

            firebaseFirestore= FirebaseFirestore.getInstance();

            firebaseFirestore.collection("grammarquiz")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){

                                Bundle extras = getIntent().getExtras();
                                subType = extras.getString("SUBTYPE");
                                type = extras.getString("TYPE");

                                for (DocumentSnapshot documentSnapshot: task.getResult()) {
                                    List<String> choices = (List<String>) documentSnapshot.get("choices");
                                    String question = documentSnapshot.get("question").toString();
                                    String answer = documentSnapshot.get("answer").toString();
                                    String type = documentSnapshot.get("type").toString();

                                    if (Objects.equals(subType, type)){
                                        arrayList.add(new GrammarQuizModel(question,choices,answer,type));

                                    }

                                }

                                totalQuestion = arrayList.size();
                                totalQuestionsTextView.setText(totalQuestion+"");
                                currentQuestionView.setText(currentQuestionIndex+1+"");

                                loadNewQuestion();







                            }

                        }
                    });


        }

        public void onClick(View view) {

            if (ansA.getText().equals(arrayList.get(currentQuestionIndex).answer)){
                rightAnswer = ansA;
            } else if (ansB.getText().equals(arrayList.get(currentQuestionIndex).answer)) {
                rightAnswer = ansB;

            } else if (ansC.getText().equals(arrayList.get(currentQuestionIndex).answer)) {
                rightAnswer = ansC;

            } else if (ansD.getText().equals(arrayList.get(currentQuestionIndex).answer)) {
                rightAnswer = ansD;
            }





            (new Handler()).postDelayed(new Runnable() {
                @SuppressLint("UseCompatLoadingForDrawables")
                @Override
                public void run() {
                    Drawable bg_style = getResources().getDrawable(R.drawable.bg_button);
                    Drawable bg_select = getResources().getDrawable(R.drawable.button_selection_bg);
                    ansA.setBackgroundDrawable(bg_style);
                    ansB.setBackgroundDrawable(bg_style);
                    ansC.setBackgroundDrawable(bg_style);
                    ansD.setBackgroundDrawable(bg_style);
                    AppCompatButton clickedButton = (AppCompatButton) view;

                    if(clickedButton.getId()==R.id.submit_btn){





                        if (selectedAnswer == null){
                            Toast.makeText(GrammarTestActivity.this, "Please select any option", Toast.LENGTH_SHORT).show();
                        } else {
                            nextBtn.setEnabled(true);
                            if(selectedAnswer.getText().toString().equals(arrayList.get(currentQuestionIndex).answer)){
                                rightAnswer.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selection_right));
                            } else{

                                wrongAnswer.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selection_wrong));
                                rightAnswer.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selection_right));
                            }
                            ansA.setEnabled(false);
                            ansB.setEnabled(false);
                            ansC.setEnabled(false);
                            ansD.setEnabled(false);

                        }



                    }
                    else if(clickedButton.getId() == R.id.next_btn){

                            clickedButton.setBackgroundDrawable(bg_select);
                            if (selectedAnswer.getText().toString().equals(arrayList.get(currentQuestionIndex).answer)){
                                score++;
                            }
                            submitBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_button));
                            currentQuestionIndex++;
                            loadNewQuestion();

                            currentQuestionView.setText(currentQuestionIndex + 1 +"");
                            clickedButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background_style_mirror));



                    } else {
                        submitBtn.setBackgroundDrawable(bg_style);
                        selectedAnswer = clickedButton;
                        clickedButton.setBackgroundDrawable(bg_select);

                        if (!selectedAnswer.getText().toString().equals(arrayList.get(currentQuestionIndex).answer)){
                            wrongAnswer = selectedAnswer;
                        }

                        nextBtn.setEnabled(false);

                    }
                }
            },200);


        }



        @SuppressLint("UseCompatLoadingForDrawables")
        void loadNewQuestion(){

            submitBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background_style));

            ansA.setEnabled(true);
            ansB.setEnabled(true);
            ansC.setEnabled(true);
            ansD.setEnabled(true);
            nextBtn.setEnabled(false);

            selectedAnswer = null;



            if(currentQuestionIndex == totalQuestion ){
                finishQuiz();
                return;
            }

            questionTextView.setText(arrayList.get(currentQuestionIndex).question);
            ansA.setText(arrayList.get(currentQuestionIndex).choices.get(0));
            ansB.setText(arrayList.get(currentQuestionIndex).choices.get(1));
            ansC.setText(arrayList.get(currentQuestionIndex).choices.get(2));
            ansD.setText(arrayList.get(currentQuestionIndex).choices.get(3));

        }

        void finishQuiz(){

            PreferenceManager preferenceManager = new PreferenceManager(getApplicationContext());
            preferenceManager.putString(subType, score+"");

            firebaseFirestore.collection("userScores")
                    .whereEqualTo("TYPE",type)
                    .whereEqualTo("SUBTYPE", subType)
                    .whereEqualTo("USERID", FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        if (!task.getResult().isEmpty()){
                            firebaseFirestore.collection("userScores").document(task.getResult().getDocuments().get(0).getId()).update("SCORE", score);
                        } else {
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("TYPE", type);
                            hashMap.put("SUBTYPE", subType);
                            hashMap.put("SCORE", score);
                            hashMap.put("USERID", FirebaseAuth.getInstance().getCurrentUser().getUid());
                            firebaseFirestore.collection("userScores").add(hashMap);
                        }
                    }
                }
            });


            new AlertDialog.Builder(this)
                    .setTitle("Result")
                    .setMessage("Score is "+ score+" out of "+ totalQuestion)
                    .setPositiveButton("Finish",(dialogInterface, i) -> finish() )
                    .setCancelable(false)
                    .show();

                    /*{
                        Intent intent = new Intent(getApplicationContext(), GrammarActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("OPENTYPE", type);
                        startActivity(intent);
                    }*/

        }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }

}