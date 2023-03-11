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

                Intent intent = new Intent(getApplicationContext(),StudyOptionsActivity.class);
                startActivity(intent);

            }
        });

        /*intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });*/

    }

   /* private void showDialog(){
        ConstraintLayout dialogConstraintLayout = findViewById(R.id.dialog_box_layout);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_box, dialogConstraintLayout);
        AppCompatButton finishDialog = findViewById(R.id.dialog_finish);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        finishDialog.findViewById(R.id.dialog_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                finish();
            }
        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }*/
}