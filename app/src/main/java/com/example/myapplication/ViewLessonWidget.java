package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ViewLessonWidget extends LinearLayout {

    TextView goToButton;
    private String name;
    TextView viewLessonName;
    private String information;


    public ViewLessonWidget(Context context, String name, String info) {
        super(context);
        LayoutInflater inflater = context.getSystemService(LayoutInflater.class);
        View v = inflater.inflate(R.layout.view_lesson_widget, this, true);
        this.name = name;
        this.information = info;
        init(context);

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

        viewLessonName = findViewById(R.id.informationMainWidget);

        goToButton = findViewById(R.id.goToActivityButton);
        goToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, LessonActivity.class);
                i.putExtra("NAME", name);
                i.putExtra("INFO", information);

                context.startActivity(i);

            }
        });

    }

    public void setName(String temp){
        name = temp;
        viewLessonName.setText(name);
    }

    public void setInformation(String temp){
        information = temp;

    }
}
