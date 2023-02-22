package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class GrammarActivity extends AppCompatActivity {

    ViewLessonWidget viewLessonWidget;
    LinearLayout linearLayout;

    String nouns;
    String verbs;
    String adjectives;
    String prepositions;
    String interjections;

    ArrayList<ViewLessonWidget> viewLessonWidgetArrayList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);
        TextView back = findViewById(R.id.back);
        linearLayout = findViewById(R.id.lessonLinearLayout);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        viewLessonWidgetArrayList = new ArrayList<>();

        ViewLessonWidget obj1 = new ViewLessonWidget(this, "nouns","test", "nouns");
        ViewLessonWidget obj2 = new ViewLessonWidget(this, "verbs", "test", "verbs");
        ViewLessonWidget obj3 = new ViewLessonWidget(this, "adjectives", "test", "adjectives");
        ViewLessonWidget obj4 = new ViewLessonWidget(this, "prepositions", "test", "prepositions");
        ViewLessonWidget obj5 = new ViewLessonWidget(this, "interjections", "test", "interjections");

        viewLessonWidgetArrayList.add(obj1);
        viewLessonWidgetArrayList.add(obj2);
        viewLessonWidgetArrayList.add(obj3);
        viewLessonWidgetArrayList.add(obj4);
        viewLessonWidgetArrayList.add(obj5);

        drawWidgets(viewLessonWidgetArrayList);

    /*  viewLessonWidget.setName("Simple Present Tense");
        viewLessonWidget.setType("");
        viewLessonWidget.setInformation("Simple present tense conveys action in the present. It is applied to show a habitual action or general truth. Predominantly, we use the present tense to describe an action occurring in the present time.\n \nFor instance,\n \nI cook every day.\n" +
                "The sun rises in the east.");*/


     /*viewLessonWidget1.setName("Present Continuous Tense");
        viewLessonWidget1.setInformation("Present continuous tense is used to delineate an action that is taking place at the current time of speaking. It is also used to depict something that we have arranged to do in the future. The structure of this tense is is/am/are + main verb + ing. \n\nFor example \n\nThe boys are playing cricket on the ground.\n" +
                "The train is going to arrive at 6:00 tomorrow morning.");


        viewLessonWidget1.setName("Present Perfect Tense");
        viewLessonWidget1.setInformation("Present perfect tense is used when we have to describe an action that has been completed in the immediate past or that occurred at an indefinite time in the past, or that began in the past but continues in the present. The structure of this tense is has/have + main verb + ed or en.\n\nFor instance,\n\nHave you ever been to Europe?\n" +
                "Gujarat has been a state since 1960.");


        viewLessonWidget1.setName("Present Perfect Continuous Tense");
        viewLessonWidget1.setInformation("Present perfect continuous tense depicts an action that started in the past, is continuing at present, and may continue in the future. The structure of this tense is has/have + been + main verb + ing. \n\nFor example,\n\nIt has been raining for the past two days.\n" +
                "She has been watching TV for the last four hours.");


        viewLessonWidget1.setName("Simple Past Tense");
        viewLessonWidget1.setInformation("Simple past tense refers to an action that was done in the past. Use this tense to show an event or action that took place at a specific time in the past and now it’s over.\n\nFor example,\n\nI worked in the IT sector.\n" +
                "The milk used to cost 10 rupees per liter some years ago.");


        viewLessonWidget1.setName("Past Continuous Tense");
        viewLessonWidget1.setInformation("Past continuous tense describes in progress action in the past when another past action takes place. The structure of this tense is was/were + main verb + ing. \n\nFor instance,\n\nAlex was playing video games when his mother arrived home.\n" +
                "Seema was waiting at the airport when her brother got off the plane.");


        viewLessonWidget1.setName("Past Perfect Tense");
        viewLessonWidget1.setInformation("Past perfect tense refers to an action that leads up to another in the past. The more contemporary past event is expressed in the simple past, and the earlier past event is expressed in the past perfect. The structure of this tense is had + main verb + ed or en.\n\nFor example,\n\nRichard had lived in Europe, before he moved to Australia.\n" +
                "He had visited his sister before he went for the study in the USA.");


        viewLessonWidget1.setName("Past Perfect Continuous Tense");
        viewLessonWidget1.setInformation("Past perfect continuous tense describes an ongoing action in the past that is completed before another action takes place in the past. The structure of this tense is had + been + main verb + ing.\n\nFor instance,\n\nShe had been studying all day.\n" +
                "Lily had been working in Google for three years when she lost her job.");


        viewLessonWidget1.setName("Simple Future Tense");
        viewLessonWidget1.setInformation("Simple future tense refers to an action that will take place in the future. Generally, it is used to describe a prediction or intention.\n\nFor example,\n\nI am going to move to the USA to pursue my masters.\n" +
                "She will help me tomorrow to complete my assignments.");


        viewLessonWidget1.setName("Future Continuous Tense");
        viewLessonWidget1.setInformation("Future continuous tense describes an action that is taking place in the present and will continue taking place in the future also. The structure of this tense is will be/shall be + main verb + ing.\n\nFor instance,\n\nI will be studying tomorrow.\n" +
                "We shall not be watching a movie today.");


        viewLessonWidget1.setName("Future Perfect Tense");
        viewLessonWidget1.setInformation("Future perfect tense is used to describe an action that precedes another in the future. The structure of this tense is will/shall + have + main verb + ed or en.\n\nFor instance,\n\nBy the end of this year, the president will have established new industries in the country.\n" +
                "They will have finished their work by tomorrow.");


        viewLessonWidget1.setName("Future Perfect Continuous Tense");
        viewLessonWidget1.setInformation("Future perfect continuous tense refers to an ongoing action that will take place prior to a certain time in the future. The structure of this tense is will have + been + main verb + ing.\n\nFor example,\n\nI will not have been staying in India by next year.\n" +
                "He will have been dancing for two hours.");
*/
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
