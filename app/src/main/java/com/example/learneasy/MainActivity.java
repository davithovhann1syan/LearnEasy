package com.example.learneasy;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView beginner = findViewById(R.id.beginner);
        TextView intermediate = findViewById(R.id.intermediate);
        TextView advanced = findViewById(R.id.advanced);
        AppCompatButton test = findViewById(R.id.take_a_test);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EnglishLevelTestActivity.class);
                startActivity(intent);
            }
        });

        beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity("beginner");

            }
        });

        intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity("intermediate");
            }
        });

        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity("advanced");
            }
        });

    }

    void startActivity(String globalType){
        Intent intent = new Intent(getApplicationContext(), StudyOptionsActivity.class);
        intent.putExtra("GLOBALTYPE", globalType);
        startActivity(intent);
    }
}

