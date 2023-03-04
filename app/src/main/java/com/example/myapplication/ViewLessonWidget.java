package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ViewLessonWidget extends LinearLayout {

    TextView goToButton;
    String name;

    String type;
    TextView viewLessonName;
    String information;

    ProgressBar progressBar;

    String subType;

    String score = "0";

    public ViewLessonWidget(Context context, String name, String info, String type, String subType, String score) {
        super(context);
        LayoutInflater inflater = context.getSystemService(LayoutInflater.class);
        View v = inflater.inflate(R.layout.view_lesson_widget, this, true);
        this.name = name;
        this.information = info;
        init(context);
        this.type = type;
        viewLessonName.setText(name);
        this.subType = subType;
        this.score = score;
        progressBar.setProgress(Integer.parseInt(score));

    }

    public ViewLessonWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = context.getSystemService(LayoutInflater.class);
        View v = inflater.inflate(R.layout.view_lesson_widget, this, true);
        init(context);
    }

    public ViewLessonWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = context.getSystemService(LayoutInflater.class);
        View v = inflater.inflate(R.layout.view_lesson_widget, this, true);
        init(context);
    }

    public ViewLessonWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater inflater = context.getSystemService(LayoutInflater.class);
        View v = inflater.inflate(R.layout.view_lesson_widget, this, true);
        init(context);
    }



    private void init(Context context){

        viewLessonName = findViewById(R.id.title);
        progressBar = findViewById(R.id.simple_progress_bar);

        goToButton = findViewById(R.id.test_button);
        goToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GrammarTestActivity.class);
                intent.putExtra("SUBTYPE", subType);
                intent.putExtra("TYPE", type);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

        viewLessonName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LessonActivity.class);
                intent.putExtra("NAME", name);
                intent.putExtra("INFO", information);
                intent.putExtra("TYPE", type);
                intent.putExtra("SUBTYPE", subType);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    public void setName(String temp){
        name = temp;
        viewLessonName.setText(name);
    }

    public void setType(String temp){
        type = temp;
    }

    public void setInformation(String temp){
        information = temp;

    }
    public String getType(){
        return type;
    }
}
