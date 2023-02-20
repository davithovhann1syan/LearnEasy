package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class VocabularyTestActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView, goBack;
    AppCompatButton ansA, ansB, ansC, ansD;
    Button submitBtn;
    ImageView questionImg;

    int score=0;
    int totalQuestion = QuestionAnswerGrammar.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_test);

        goBack = findViewById(R.id.back);
        totalQuestionsTextView = findViewById(R.id.total_questions);
        questionImg = findViewById(R.id.question_image);
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

        totalQuestionsTextView.setText("Total questions : "+totalQuestion);

        loadNewQuestion();

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



    @Override
    public void onClick(View view) {

        Drawable d = getResources().getDrawable(R.drawable.button_background_style);
        ansA.setBackgroundDrawable(d);
        ansB.setBackgroundDrawable(d);
        ansC.setBackgroundDrawable(d);
        ansD.setBackgroundDrawable(d);

        AppCompatButton clickedButton = (AppCompatButton) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(QuestionAnswerVocabulary.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();


        }else{
            //choices button clicked
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackground(getResources().getDrawable(R.drawable.button_selection_bg));

        }

    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        //questionTextView.setText(QuestionAnswerVocabulary.question[currentQuestionIndex]);
        questionImg.setImageResource(QuestionAnswerVocabulary.images[currentQuestionIndex]);
        ansA.setText(QuestionAnswerVocabulary.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswerVocabulary.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswerVocabulary.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswerVocabulary.choices[currentQuestionIndex][3]);

    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                /*.setPositiveButton("Restart quiz",(dialogInterface, i) -> restartQuiz() )*/
                .setPositiveButton("View wrong answers",(dialogInterface, i) -> worngAnswerPage() )
                .setCancelable(false)
                .show();


    }

    private void worngAnswerPage() {
        Intent intent = new Intent(getApplicationContext(), VocabularyWrongAnswers.class);
        startActivity(intent);
    }

   /* void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }*/





}
