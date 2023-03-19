package com.example.learneasy;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnglishLevelTestActivity extends AppCompatActivity implements View.OnClickListener{

    int bestScore = 0;

    FirebaseFirestore firebaseFirestore;

    TextView totalQuestionsTextView, currentQuestionView, goBack;
    TextView questionTextView;
    AppCompatButton ansA, ansB, ansC, ansD;
    AppCompatButton submitBtn;


    int score = 0;
    int totalQuestion = 1;
    int currentQuestionIndex = 0;
    String level = "?";

    int submitClicks = 0;


    AppCompatButton rightAnswer, wrongAnswer, selectedAnswer;


    ArrayList<EnglishLevelQuizModel> arrayList = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_test_activity);

        goBack = findViewById(R.id.back);
        totalQuestionsTextView = findViewById(R.id.total_questions);
        currentQuestionView = findViewById(R.id.current_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        totalQuestionsTextView.setText("Total questions : " + totalQuestion);

        Log.i("DAS", level);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("englishleveltest")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot: task.getResult()) {
                                String question = documentSnapshot.get("question").toString();
                                List<String> choices = (List<String>) documentSnapshot.get("choices");
                                String answer = documentSnapshot.get("answer").toString();
                                arrayList.add(new EnglishLevelQuizModel(question,choices,answer));

                            }

                            Collections.shuffle(arrayList);

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
                Drawable bg_style = getResources().getDrawable(R.drawable.button_background_style);
                Drawable bg_select = getResources().getDrawable(R.drawable.button_selection_bg);
                ansA.setBackgroundDrawable(bg_style);
                ansB.setBackgroundDrawable(bg_style);
                ansC.setBackgroundDrawable(bg_style);
                ansD.setBackgroundDrawable(bg_style);
                AppCompatButton clickedButton = (AppCompatButton) view;

                if(clickedButton.getId()==R.id.submit_btn){

                    submitClicks++;




                    if (submitClicks == 1){
                        if (selectedAnswer == null){

                            Toast.makeText(EnglishLevelTestActivity.this, "Please select any option", Toast.LENGTH_SHORT).show();
                        } else {
                            submitBtn.setText("NEXT");
                            if (selectedAnswer.getText().toString().equals(arrayList.get(currentQuestionIndex).answer)) {
                                rightAnswer.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selection_right));
                                score++;
                            } else {
                                wrongAnswer.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selection_wrong));
                                rightAnswer.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selection_right));
                            }

                            ansA.setEnabled(false);
                            ansB.setEnabled(false);
                            ansC.setEnabled(false);
                            ansD.setEnabled(false);
                        }
                    }

                    if (submitClicks == 2){
                        currentQuestionIndex++;
                        currentQuestionView.setText(currentQuestionIndex + 1 + "");
                        loadNewQuestion();
                    }

                }

                else {
                    submitBtn.setBackgroundDrawable(bg_style);
                    selectedAnswer = clickedButton;
                    clickedButton.setBackgroundDrawable(bg_select);

                    if (!selectedAnswer.getText().toString().equals(arrayList.get(currentQuestionIndex).answer)){
                        wrongAnswer = selectedAnswer;
                    }

                }
            }
        },200);


    }



    @SuppressLint("UseCompatLoadingForDrawables")
    void loadNewQuestion(){

        submitBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background_style));

        ansA.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background_style));
        ansB.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background_style));
        ansC.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background_style));
        ansD.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background_style));

        submitBtn.setText("SUBMIT");

        submitClicks = 0;

        ansA.setEnabled(true);
        ansB.setEnabled(true);
        ansC.setEnabled(true);
        ansD.setEnabled(true);

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

        submitBtn.setEnabled(false);
        ansA.setEnabled(false);
        ansB.setEnabled(false);
        ansC.setEnabled(false);
        ansD.setEnabled(false);


        if (arrayList.size() == 0){
            alertDialogEmpty();
        } else {
            alertDialog();
        }

    }

    @SuppressLint("SetTextI18n")

    void alertDialog(){

        String passStatus = "Result";

        float x = (float) score / totalQuestion;

        if (x < 0.5){
            level = "beginner";

        }  else if (x >= 0.8) {
            level = "advanced";
        }
        else {
            level = "intermediate";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(EnglishLevelTestActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_result, null);
        TextView resultField = dialogView.findViewById(R.id.score_text);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        if (dialog.getWindow() != null){
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        dialog.show();

        resultField.setText("Your score is " + score + " out of " + totalQuestion + "so your english level is approximately: " + passStatus);


        dialogView.findViewById(R.id.dialog_finish).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });

        dialogView.findViewById(R.id.restart_quiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = 0;
                currentQuestionIndex = 0;
                loadNewQuestion();
                dialog.dismiss();
            }
        });
    }

    void alertDialogEmpty(){
        AlertDialog.Builder builder = new AlertDialog.Builder(EnglishLevelTestActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_not_ready, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        if (dialog.getWindow() != null){
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        dialog.show();
        dialogView.findViewById(R.id.dialog_finish).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });

    }

}